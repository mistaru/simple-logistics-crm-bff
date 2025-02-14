package kg.founders.core.services.auth.role.permission;

import kg.founders.core.model.auth.role.permission.LogisticPermissionModel;

import java.util.List;

public interface PermissionService {
    List<LogisticPermissionModel> listAllAsModel();
}
