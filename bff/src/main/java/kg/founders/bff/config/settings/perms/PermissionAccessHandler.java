package kg.founders.bff.config.settings.perms;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import kg.founders.bff.config.settings.TokenContextHolder;
import kg.founders.bff.config.settings.grant.GrantService;
import kg.founders.core.enums.permission.AccessType;
import kg.founders.core.enums.permission.PermissionType;
import kg.founders.core.exceptions.ForbiddenException;
import kg.founders.core.settings.security.permission.ImmutableAccessPermission;
import kg.founders.core.settings.security.permission.annotation.HasAccess;
import kg.founders.core.settings.security.permission.annotation.HasPermission;
import kg.founders.core.settings.security.permission.annotation.HasPermissions;
import kg.founders.core.settings.security.permission.annotation.ManualPermissionControl;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PermissionAccessHandler extends HandlerInterceptorAdapter {

    GrantService grant;
    Gson gson;
    RequestMatcher ignoringRequestMatcher;
    Map<Method, Map<PermissionType, Integer>> cachedPermissions;
    Set<Method> excepts;
    Set<Method> badConfiguredMethods;

    public PermissionAccessHandler(
            GrantService grant,
            Gson gson,
            RequestMatcher ignoringRequestMatcher
    ) {
        this.grant = grant;
        this.gson = gson;
        this.ignoringRequestMatcher = ignoringRequestMatcher;
        cachedPermissions = new ConcurrentHashMap<>();
        excepts = Sets.newConcurrentHashSet();
        badConfiguredMethods = Sets.newConcurrentHashSet();
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {
        if (ignoringRequestMatcher.matches(request) || !(handler instanceof HandlerMethod)) {
            return true;
        }

        var handlerMethod = (HandlerMethod) handler;

        var method = handlerMethod.getMethod();

        var permsMap = cachedPermissions.get(method);
        if (permsMap == null) {
            if (!excepts.contains(method)) {
                if (badConfiguredMethods.contains(method)) {
                    respond(response, HttpStatus.INTERNAL_SERVER_ERROR, "Method has no security annotations");
                    return false;
                }

                if (handlerMethod.getMethodAnnotation(ManualPermissionControl.class) != null) {
                    excepts.add(method);
                } else {
                    if (!checkMethod(handlerMethod) && !checkClass(handlerMethod)) {
                        badConfiguredMethods.add(method);
                        respond(response, HttpStatus.INTERNAL_SERVER_ERROR, "Method has no security annotations");
                        return false;
                    } else {
                        return checkHasAccess(request, response, cachedPermissions.get(method));
                    }
                }
            }
        } else {
            return checkHasAccess(request, response, permsMap);
        }

        return true;
    }

    private boolean checkHasAccess(HttpServletRequest request, HttpServletResponse response, Map<PermissionType, Integer> permsMap) throws IOException {
        var perms = permsMap.entrySet();
        var noneMatch = perms.stream()
                .noneMatch(
                        perm -> grant.matchAny(perm.getKey(), perm.getValue())
                );

        if (noneMatch) {
            respond(response, HttpStatus.FORBIDDEN, ForbiddenException.DEFAULT_MESSAGE);
            return false;
        } else {
            putMethodGrantHolder(perms.stream().collect(Collectors.toMap(
                    Map.Entry::getKey,
                    entry -> new ImmutableAccessPermission(entry.getValue())
            )));
            return true;
        }
    }

    private void putMethodGrantHolder(Map<PermissionType, ImmutableAccessPermission> methodGrantHolder) {
        TokenContextHolder.currentOptional()
                .orElseThrow(IllegalStateException::new)
                .setMethodGrantHolder(methodGrantHolder);
    }

    private void respond(HttpServletResponse response, HttpStatus status, String message) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(status.value());
        response.getWriter().write(gson.toJson(Collections.singletonMap("message", message)));
        response.flushBuffer();
    }

    private boolean checkMethod(HandlerMethod handlerMethod) {
        var hasPermissions = handlerMethod.getMethodAnnotation(HasPermissions.class);

        if (hasPermissions == null) {
            hasPermissions = (HasPermissions) Stream.of(handlerMethod.getBeanType().getDeclaredAnnotations())
                    .filter(a -> a.annotationType() == HasPermissions.class)
                    .findFirst()
                    .orElse(null);
        }

        if (hasPermissions != null) {
            cachedPermissions.putIfAbsent(
                    handlerMethod.getMethod(),
                    Stream.of(hasPermissions.value())
                            .collect(Collectors.toMap(
                                    HasPermission::value,
                                    a -> AccessType.combine(a.access())
                            ))
            );

            return true;
        }

        var hasPermission = handlerMethod.getMethodAnnotation(HasPermission.class);

        if (hasPermission != null) {
            cachedPermissions.putIfAbsent(
                    handlerMethod.getMethod(),
                    Map.of(
                            hasPermission.value(),
                            AccessType.combine(hasPermission.access())
                    )
            );

            return true;
        }

        return false;
    }

    private boolean checkClass(HandlerMethod handlerMethod) {
        return Stream.of(handlerMethod.getBeanType().getDeclaredAnnotations())
                .filter(a -> a.annotationType() == HasPermission.class)
                .findFirst()
                .map(a -> (HasPermission) a)
                .map(permission -> {

                    var access = handlerMethod.getMethodAnnotation(HasAccess.class);

                    if (access == null) {
                        cachedPermissions.putIfAbsent(
                                handlerMethod.getMethod(),
                                Map.of(
                                        permission.value(),
                                        AccessType.combine(permission.access())
                                )
                        );
                    } else {
                        cachedPermissions.putIfAbsent(
                                handlerMethod.getMethod(),
                                Map.of(
                                        permission.value(),
                                        AccessType.combine(access.value())
                                )
                        );
                    }

                    return true;
                }).orElse(false);
    }
}
