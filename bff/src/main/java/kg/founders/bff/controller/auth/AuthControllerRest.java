package kg.founders.bff.controller.auth;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kg.founders.bff.config.response.ResponseMessage;
import kg.founders.bff.config.response.ResultCode;
import kg.founders.bff.config.settings.TokenContextHolder;
import kg.founders.core.entity.auth.LogisticAuth;
import kg.founders.core.exceptions.ForbiddenException;
import kg.founders.core.exceptions.UserNotFoundException;
import kg.founders.core.model.audit.AuditModel;
import kg.founders.core.model.auth.LogisticAuthModel;
import kg.founders.core.model.login.LoginModel;
import kg.founders.core.model.login.PasswordChangeModel;
import kg.founders.core.services.auth.AuthService;
import kg.founders.core.services.login.LoginService;
import kg.founders.core.settings.security.permission.annotation.ManualPermissionControl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@RestController
@RequestMapping("/api")
public class AuthControllerRest {
    AuthService authService;
    LoginService loginService;
    AuditorAware<AuditModel> auditorAware;

    public AuthControllerRest(AuthService authService, LoginService loginService) {
        this.authService = authService;
        this.loginService = loginService;
    }

    @PostMapping(value = "/public/auth/login")
    @ApiOperation("Возвращает токен")
    public ResponseMessage<String> login(
            @ApiParam(value = "Модель с логином и паролем", required = true)
            @RequestBody LoginModel model,
            HttpServletRequest request
    ) {
        try {
            return new ResponseMessage<>(loginService.login(
                    model,
                    request.getRemoteHost()
            ), ResultCode.OK);
        } catch (UserNotFoundException e) {
            return new ResponseMessage<>(null, ResultCode.NOT_FOUND, e.getMessage());
        }

    }

    @ManualPermissionControl
    @ApiOperation("Возвращает обновленный токен")
    @GetMapping(value = "/auth/refreshToken")
    public ResponseMessage<String> refreshToken(HttpServletRequest request) {
        return new ResponseMessage<>(loginService.refreshToken(request.getHeader(AUTHORIZATION).split(" ")[1]), ResultCode.OK);
    }

    @ManualPermissionControl
    @GetMapping("/auth/current")
    public LogisticAuthModel current() {
        return TokenContextHolder.currentOptional().map(
                context -> {
                    var auth = context.getPrincipal();
                    return authService.toModel(auth);
                }
        ).orElseThrow(ForbiddenException::new);
    }

    @ManualPermissionControl
    @GetMapping("/auth/activateRole")
    public void updateRole(@RequestParam Long roleId) {
        TokenContextHolder.currentOptional().ifPresentOrElse(
                context -> {
                    var auth = context.getPrincipal();
                    authService.updateActiveRole(auth.getId(), roleId);
                }, () -> {
                    throw new ForbiddenException();
                }
        );
    }


    @ManualPermissionControl
    @PostMapping(value = "/auth/password")
    public ResponseMessage<String> changePassword(@RequestBody PasswordChangeModel model) {
        try {
            LogisticAuth logisticAuth = TokenContextHolder.currentOptional().orElseThrow(ForbiddenException::new).getPrincipal();
            authService.updatePassword(
                    logisticAuth,
                    model,
                    auditorAware.getCurrentAuditor().orElseThrow(ForbiddenException::new)
            );
            return new ResponseMessage<>("Пароль успешно изменен", ResultCode.OK);
        } catch (Exception e) {
            log.error("AuthControllerRest : changePassword {}", e.getMessage());
            return new ResponseMessage<>("Ошибка при изменении пароля - " + e.getMessage(), ResultCode.FAIL);
        }
    }

}