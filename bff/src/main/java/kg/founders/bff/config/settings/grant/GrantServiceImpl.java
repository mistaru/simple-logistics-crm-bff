package kg.founders.bff.config.settings.grant;

import kg.founders.bff.config.settings.TokenContextHolder;
import kg.founders.core.entity.auth.permission.GrantHolder;
import kg.founders.core.enums.permission.AccessType;
import kg.founders.core.enums.permission.PermissionType;
import kg.founders.core.exceptions.ForbiddenException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.function.Function;

@Service
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GrantServiceImpl implements GrantService {

    private static GrantService instance;

    public static GrantService getInstance() {
        return instance;
    }

    @PostConstruct
    void init() {
        instance = this;
    }

    private boolean filter(Function<GrantHolder, Boolean> filterCondition) {
        var auth = TokenContextHolder.current().getPrincipal();
            return auth.getAuthorities()
                    .stream()
                    .anyMatch(filterCondition::apply);
    }

    @Override
    public boolean matchAny(PermissionType permission, int accessTypeMask) {
        return filter(grantHolder ->
                grantHolder.getPermission() == permission &&
                grantHolder.hasAny(accessTypeMask));
    }

    @Override
    public boolean matchAll(PermissionType permission, int accessTypeMask) {
        return filter(grantHolder ->
                grantHolder.getPermission() == permission &&
                grantHolder.hasAll(accessTypeMask));
    }

    @Override
    public boolean hasAny(PermissionType permission, AccessType... accessTypes) {
        return filter(
                grantHolder ->
                        grantHolder.getPermission() == permission &&
                        grantHolder.hasAny(accessTypes));
    }

    @Override
    public boolean hasAny(AccessType accessType, EnumSet<PermissionType> permissions) {
        return filter(
                grantHolder ->
                        permissions.contains(grantHolder.getPermission()) &&
                        grantHolder.hasAny(accessType)
        );
    }

    @Override
    public boolean hasAny(AccessType accessType, PermissionType... permissions) {
        EnumSet<PermissionType> permissionSet = EnumSet.copyOf(Arrays.asList(permissions));
        return hasAny(accessType, permissionSet);
    }

    @Override
    public void checkHasAny(PermissionType permission, AccessType... accessTypes) {
        if (!hasAny(permission, accessTypes)) {
            throw new ForbiddenException();
        }
    }

    @Override
    public void checkHasAny(AccessType accessType, PermissionType... permissions) {
        if (!hasAny(accessType, permissions)) {
            throw new ForbiddenException();
        }
    }

    @Override
    public void checkHasAny(AccessType accessType, EnumSet<PermissionType> permissions) {
        if (!hasAny(accessType, permissions)) {
            throw new ForbiddenException();
        }
    }
}