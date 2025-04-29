package kg.founders.core.util;

import kg.founders.core.entity.auth.LogisticAuth;
import kg.founders.core.exceptions.ForbiddenException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class PermissionHelper {

    public LogisticAuth getCurrentPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof LogisticAuth) {
            return (LogisticAuth) principal;
        } else {
            throw new ForbiddenException();
        }
    }

    public boolean isAdmin() {
        return getCurrentPrincipal().getLogisticAuthRoles().stream()
                .anyMatch(role -> "admin".equalsIgnoreCase(role.getLogisticRole().getName()));
    }

    public Long currentUserId() {
        return getCurrentPrincipal().getId();
    }
}
