package kg.founders.core.services.auth;

import kg.founders.core.entity.auth.LogisticAuth;
import kg.founders.core.model.audit.AuditModel;
import kg.founders.core.model.auth.LogisticAuthModel;
import kg.founders.core.model.login.PasswordChangeModel;

import java.util.List;
import java.util.Optional;

public interface AuthService {

    Optional<LogisticAuth> findByUsername(String username);

    Optional<LogisticAuth> findById(Long authId);

    boolean blockAuth(Long authId, boolean block);

    boolean blockAuth(String username, boolean block);

    void updatePassword(LogisticAuth bankAuth, PasswordChangeModel model, AuditModel auditModel);

    LogisticAuth save(LogisticAuthModel bankAuth);

    LogisticAuth save(LogisticAuth bankAuth);

    Boolean isBlocked(Long id);

    String hashPassword(String password);

    LogisticAuthModel toModel(LogisticAuth logisticAuth);

    void updateActiveRole(Long id, Long roleId);

    List<LogisticAuthModel> findAll();

    void deleteByAuthId(Long authId);
}
