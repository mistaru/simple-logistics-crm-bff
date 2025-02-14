package kg.founders.core.data_access_layer.repo.permission;

import kg.founders.core.entity.auth.permission.LogisticRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionRepo extends JpaRepository<LogisticRolePermission, Long> {
}
