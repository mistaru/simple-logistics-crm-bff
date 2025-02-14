package kg.founders.core.entity.auth.permission;

import kg.founders.core.enums.permission.PermissionType;
import kg.founders.core.settings.security.permission.AccessPermission;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;

@Value
public class GrantHolder implements GrantedAuthority, AccessPermission {

    PermissionType permission;
    int access;

    @Override
    public int getAccess() {
        return access;
    }

    @Override
    public String getAuthority() {
        return permission.name();
    }
}