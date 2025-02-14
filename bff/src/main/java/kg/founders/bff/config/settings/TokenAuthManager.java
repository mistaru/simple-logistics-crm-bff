package kg.founders.bff.config.settings;

import kg.founders.core.exceptions.ForbiddenException;
import kg.founders.core.services.auth.AuthService;
import kg.founders.core.services.login.LoginService;
import kg.founders.core.settings.jwt.TokenService;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
public final class TokenAuthManager implements AuthenticationManager {

    LoginService loginService;
    TokenService jwt;
    AuthService authService;

    public TokenAuthManager(
            LoginService loginService,
            TokenService jwt,
            AuthService authService) {
        this.loginService = loginService;
        this.jwt = jwt;
        this.authService = authService;
    }

    @Override
    public TokenContextHolder authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();
        var auth = loginService.authFromToken(token);

        if (auth == null) {
            throw new UsernameNotFoundException("User not found");
        }

        if (authService.findByUsername(auth.getUsername()).isEmpty()) {
            throw new ForbiddenException();
        }

        if (!jwt.verify(token, auth.getUsername(), auth.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }

        return new TokenContextHolder(token, auth);
    }
}