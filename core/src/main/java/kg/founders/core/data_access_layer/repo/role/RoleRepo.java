package kg.founders.core.data_access_layer.repo.role;

import kg.founders.core.entity.auth.role.LogisticRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepo extends JpaRepository<LogisticRole, Long> {

    Optional<LogisticRole> getByIdAndRdtIsNull(Long id);

    List<LogisticRole> findByRdtIsNull();
}
