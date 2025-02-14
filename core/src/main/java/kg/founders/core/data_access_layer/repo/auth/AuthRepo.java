package kg.founders.core.data_access_layer.repo.auth;

import kg.founders.core.entity.auth.LogisticAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepo extends JpaRepository<LogisticAuth, Long> {
    Optional<LogisticAuth> findByUsername(String username);
}
