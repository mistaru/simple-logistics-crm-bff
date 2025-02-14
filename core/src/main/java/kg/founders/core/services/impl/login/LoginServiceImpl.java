package kg.founders.core.services.impl.login;


import com.google.common.base.Strings;
import com.lambdaworks.crypto.SCryptUtil;
import kg.founders.core.exceptions.*;
import kg.founders.core.entity.auth.LogisticAuth;
import kg.founders.core.model.login.LoginModel;
import kg.founders.core.services.auth.AuthService;
import kg.founders.core.services.login.LoginService;
import kg.founders.core.services.login.LoginHistoryService;
import kg.founders.core.settings.jwt.TokenService;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class LoginServiceImpl implements LoginService {

    AuthService authService;
    LoginHistoryService loginHistoryService;
    TokenService jwt;

    public LoginServiceImpl(
            AuthService authService,
            LoginHistoryService loginHistoryService,
            TokenService jwt
    ) {
        this.authService = authService;
        this.loginHistoryService = loginHistoryService;
        this.jwt = jwt;
    }

    @Override
    public String login(LoginModel loginModel, String ip) {
        log.debug("login(): with username '{}', ip: '{}'", loginModel.getUsername(), ip);
        try {
            LogisticAuth logisticAuth = verify(loginModel.getUsername(), loginModel.getPassword());
            return generateJwtCode(logisticAuth);
        } catch (BaseException e) {
            loginHistoryService.save(ip, loginModel.getUsername());
            throw e;
        }
    }

    private String generateJwtCode(LogisticAuth logisticAuth) {
        return jwt.sign(
                logisticAuth.getUsername(),
                logisticAuth.getPassword()
        );
    }

    private LogisticAuth verify(String username, String password) {
        if (Strings.isNullOrEmpty(username)) throw new EmptyUsernameException();

        if (Strings.isNullOrEmpty(password)) throw new EmptyPasswordException();

        Optional<LogisticAuth> entity = authService.findByUsername(username);

        return entity.map(
                        auth -> {
                            checkAuthForBlock(username, auth);
                            isPasswordEqualsToHashedOne(password, auth.getPassword());
                            checkPasswordExpiration(auth);
                            loginHistoryService.clear(username);
                            return auth;
                        }
                )
                .orElseThrow(UserNotFoundException::new);
    }

    private void checkAuthForBlock(String username, LogisticAuth logisticAuth) {
        if (logisticAuth.getBlocked() != null || logisticAuth.getRdt() != null) {
            throw new UserNotFoundException();
        }

        if (loginHistoryService.isLoginAttemptsExceeded(username)) {
            if (authService.blockAuth(username, true)) {
                loginHistoryService.clear(username);
            }
            throw new UserNotFoundException();
        }
    }

    private void isPasswordEqualsToHashedOne(String password, String passwordHash) {
        if (Strings.isNullOrEmpty(passwordHash) || !SCryptUtil.check(password, passwordHash)) {
            throw new UserNotFoundException();
        }
    }

    private void checkPasswordExpiration(LogisticAuth logisticAuth) {
        if (logisticAuth.getPasswordExpireDate() != null && logisticAuth.getPasswordExpireDate().before(new Timestamp(System.currentTimeMillis()))) {
            throw new PasswordExpirationException("Срок годности пароля истек, просьба обновить пароль!");
        }
    }

    @Override
    public LogisticAuth authFromToken(String token) {

        String username = jwt.getUsername(token);

        var auth = authService.findByUsername(username).orElseThrow(UserNotFoundException::new);
        if (auth.getBlocked() != null || auth.getRdt() != null) throw new UserNotFoundException();

        return auth;
    }

    @Override
    public String refreshToken(String token) {
        String username = jwt.getUsername(token);
        var auth = authService.findByUsername(username).orElseThrow(UserNotFoundException::new);

        if (!jwt.verify(token, auth.getUsername(), auth.getPassword())) {
            throw new AuthenticationException();
        }

        return jwt.sign(auth.getUsername(), auth.getPassword());
    }

}