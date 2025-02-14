package kg.founders.core.services.auth;

import kg.founders.core.entity.auth.LogisticAuth;
import kg.founders.core.entity.auth.role.LogisticRole;
import kg.founders.core.model.audit.AuditModel;
import kg.founders.core.model.auth.LogisticAuthModel;
import kg.founders.core.model.login.PasswordChangeModel;

import java.sql.Connection;
import java.util.Optional;
import java.util.Set;

public interface AuthService {

    Optional<LogisticAuth> findByUsername(String username);

    Optional<LogisticAuth> findById(Long authId);

    boolean blockAuth(String username, boolean block);

    void updatePassword(LogisticAuth logisticAuth, PasswordChangeModel model, AuditModel auditModel);

    LogisticAuth register(Connection connection, LogisticAuth logisticAuth, Set<LogisticRole> logisticRoles);

    LogisticAuth save(LogisticAuth logisticAuth);

    Boolean isBlocked(Long id);

    String hashPassword(String password);

    LogisticAuthModel toModel(LogisticAuth logisticAuth);

    void updateActiveRole(Long id, Long roleId);
}
