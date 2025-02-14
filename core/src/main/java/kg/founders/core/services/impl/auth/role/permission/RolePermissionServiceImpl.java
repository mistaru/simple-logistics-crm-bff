package kg.founders.core.services.impl.auth.role.permission;

import kg.founders.core.data_access_layer.repo.permission.RolePermissionRepo;
import kg.founders.core.entity.auth.permission.LogisticRolePermission;
import kg.founders.core.services.auth.role.permission.RolePermissionService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class RolePermissionServiceImpl implements RolePermissionService {

    RolePermissionRepo repo;

    @Override
    public void saveAll(List<LogisticRolePermission> logisticRolePermissions) {
        repo.saveAll(logisticRolePermissions);
    }

    @Override
    public void deleteAll(List<LogisticRolePermission> logisticRolePermissions) {
        repo.deleteAll(logisticRolePermissions);
    }
}