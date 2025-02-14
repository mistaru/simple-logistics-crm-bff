package kg.founders.core.services.impl.auth.role;

import kg.founders.core.data_access_layer.dao.AuthRoleDao;
import kg.founders.core.services.auth.role.AuthRoleService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthRoleServiceImpl implements AuthRoleService {

    AuthRoleDao dao;

    @Override
    public void updateActive(Long authId, Long roleId) {
        dao.updateActiveRole(authId, roleId);
    }

}
