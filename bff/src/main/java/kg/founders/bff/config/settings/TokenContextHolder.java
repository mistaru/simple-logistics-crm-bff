package kg.founders.bff.config.settings;

import kg.founders.core.entity.auth.LogisticAuth;
import kg.founders.core.enums.permission.PermissionType;
import kg.founders.core.settings.security.permission.ImmutableAccessPermission;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;
import java.util.Optional;

@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TokenContextHolder extends AbstractAuthenticationToken {
    LogisticAuth principal;
    Object credentials;

    @Getter
    @Setter
    @NonFinal
    Map<PermissionType, ImmutableAccessPermission> methodGrantHolder;

    TokenContextHolder(String token) {
        super(null);
        this.principal = null;
        this.credentials = token;
        setAuthenticated(false);
    }

    TokenContextHolder(String token, LogisticAuth principal) {
        super(principal.getAuthorities());
        this.principal = principal;
        this.credentials = token;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public LogisticAuth getPrincipal() {
        return principal;
    }

    public static Optional<TokenContextHolder> currentOptional() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof TokenContextHolder) {
            return Optional.of((TokenContextHolder) auth);
        }
        return Optional.empty();
    }

    public static TokenContextHolder current() {
        return currentOptional().orElseThrow(IllegalStateException::new);
    }
}