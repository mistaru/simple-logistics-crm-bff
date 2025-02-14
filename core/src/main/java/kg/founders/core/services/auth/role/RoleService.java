package kg.founders.core.services.auth.role;

import kg.founders.core.entity.auth.role.LogisticRole;
import kg.founders.core.model.auth.role.LogisticRoleModel;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<LogisticRoleModel> listAllAsModel();

    Optional<LogisticRole> findById(Long id);

    void save(LogisticRoleModel model);

    void delete(Long roleId);

}
