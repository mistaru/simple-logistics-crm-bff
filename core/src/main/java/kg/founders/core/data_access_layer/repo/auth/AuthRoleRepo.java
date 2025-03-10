package kg.founders.core.data_access_layer.repo.auth;

import kg.founders.core.entity.auth.role.LogisticAuthRole;
import kg.founders.core.entity.auth.role.LogisticAuthRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRoleRepo extends JpaRepository<LogisticAuthRole, LogisticAuthRoleId> {

    void deleteAllByLogisticAuthId(Long authId);
}