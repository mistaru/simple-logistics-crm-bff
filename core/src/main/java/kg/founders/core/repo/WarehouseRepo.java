package kg.founders.core.repo;

import kg.founders.core.entity.Warehouse;
import kg.founders.core.enums.CargoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface WarehouseRepo extends JpaRepository<Warehouse, Long> {
    @Query("SELECT new kg.founders.core.entity.Warehouse(" +
            "w.id, w.name, w.isLocal, w.city, w.address, w.phoneNumber, w.volumeM3, w.weightKg, " +
            "COALESCE(SUM(CASE WHEN c.status IN :statuses THEN c.volume ELSE 0 END), 0.0), " +
            "COALESCE(SUM(CASE WHEN c.status IN :statuses THEN c.weight ELSE 0 END), 0.0)) " +
            "FROM Warehouse w " +
            "LEFT JOIN w.cargoWarehouses cw " +
            "LEFT JOIN Cargo c ON c.id = cw.cargoId " +
            "GROUP BY w.id, w.name, w.isLocal, w.city, w.address, w.phoneNumber, w.volumeM3, w.weightKg " +
            "ORDER BY w.id")
    List<Warehouse> findAllWithOccupiedCapacity(@Param("statuses") Collection<CargoStatus> statuses);
}
