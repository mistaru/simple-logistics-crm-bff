package kg.founders.core.repo;

import kg.founders.core.entity.Cargo;
import kg.founders.core.enums.CargoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoRepo extends JpaRepository<Cargo, Long> {
    List<Cargo> findAllByRdtIsNullAndStatusNotIn(List<CargoStatus> statuses);
    List<Cargo> findAllByManagerId(Long managerId);

    List<Cargo> findAllByClientIdAndRdtIsNull(Long clientId);
    boolean existsByManagerId(Long managerId);

    @Query("select t.id from Cargo t where t.rdt is null")
    List<Long> findIdByRdtIsNull();

    @Modifying
    @Query("UPDATE Cargo c SET c.status = :status WHERE c.id IN :ids")
    void updateStatusForCargos(@Param("ids") List<Long> cargoIds, @Param("status") CargoStatus status);
}
