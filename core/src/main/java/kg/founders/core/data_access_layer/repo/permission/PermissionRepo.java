package kg.founders.core.data_access_layer.repo.permission;

import kg.founders.core.entity.auth.permission.LogisticPermission;
import kg.founders.core.model.auth.role.permission.LogisticPermissionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRepo extends JpaRepository<LogisticPermission, Long> {

    @Query("select new kg.founders.core.model.auth.role.permission.LogisticPermissionModel(p.id, p.name, 0) from LogisticPermission p")
    List<LogisticPermissionModel> findAllPermissions();
}