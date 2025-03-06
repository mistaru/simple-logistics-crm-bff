package kg.founders.core.services.impl.auth.role;

import kg.founders.core.data_access_layer.dao.AuthRoleDao;
import kg.founders.core.data_access_layer.repo.auth.AuthRoleRepo;
import kg.founders.core.entity.auth.LogisticAuth;
import kg.founders.core.entity.auth.role.LogisticAuthRole;
import kg.founders.core.entity.auth.role.LogisticAuthRoleId;
import kg.founders.core.entity.auth.role.LogisticRole;
import kg.founders.core.services.auth.role.AuthRoleService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthRoleServiceImpl implements AuthRoleService {

    AuthRoleDao dao;
    AuthRoleRepo repo;

    @Override
    public void updateActive(Long authId, Long roleId) {
        dao.updateActiveRole(authId, roleId);
    }

    @Override
    public List<LogisticAuthRole> saveAllByAuthId(LogisticAuth auth, List<LogisticRole> roles) {
        deleteAllByAuthId(auth.getId());
        return repo.saveAll(roles.stream().map(role -> new LogisticAuthRole(new LogisticAuthRoleId(auth.getId(), role.getId()), auth, role, false)).collect(Collectors.toList()));
    }

    @Override
    public void deleteAllByAuthId(Long authId) {
        repo.deleteAllByLogisticAuthId(authId);
    }

}