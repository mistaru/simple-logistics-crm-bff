package kg.founders.core.services.auth.role;


import kg.founders.core.entity.auth.LogisticAuth;
import kg.founders.core.entity.auth.role.LogisticAuthRole;
import kg.founders.core.entity.auth.role.LogisticRole;

import java.util.List;

public interface AuthRoleService {

    void updateActive(Long authId, Long roleId);

    List<LogisticAuthRole> saveAllByAuthId(LogisticAuth auth, List<LogisticRole> roles);

    void deleteAllByAuthId(Long authId);

}