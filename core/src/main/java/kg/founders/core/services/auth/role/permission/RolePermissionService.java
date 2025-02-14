package kg.founders.core.services.auth.role.permission;


import kg.founders.core.entity.auth.permission.LogisticRolePermission;

import java.util.List;

public interface RolePermissionService {

    void saveAll(List<LogisticRolePermission> logisticRolePermissions);

    void deleteAll(List<LogisticRolePermission> logisticRolePermissions);
}
