package kg.founders.core.repo;

import kg.founders.core.entity.LogisticOldPassword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OldPasswordRepo extends JpaRepository<LogisticOldPassword, Long> {
    List<LogisticOldPassword> findTop5ByLogisticAuthIdOrderByIdDesc(Long authId);
}