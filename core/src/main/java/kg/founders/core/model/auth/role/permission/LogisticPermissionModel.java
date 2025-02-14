package kg.founders.core.model.auth.role.permission;

import kg.founders.core.entity.auth.permission.LogisticPermission;
import kg.founders.core.enums.permission.PermissionType;
import kg.founders.core.settings.security.permission.AccessPermission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogisticPermissionModel implements AccessPermission {
    Long id;
    PermissionType name;
    int access;

    @Override
    public int getAccess() {
        return access;
    }

    public LogisticPermission fromModel() {
        return new LogisticPermission(getId());
    }
}