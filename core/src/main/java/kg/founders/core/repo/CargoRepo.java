package kg.founders.core.repo;

import kg.founders.core.entity.Cargo;
import kg.founders.core.enums.CargoStatus;
import kg.founders.core.model.CargoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Repository
public interface CargoRepo extends JpaRepository<Cargo, Long> {
    List<Cargo> findAllByRdtIsNullAndStatusNotIn(List<CargoStatus> statuses);
}
