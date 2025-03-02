package kg.founders.core.repo;

import kg.founders.core.entity.CargoWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoWarehouseRepo extends JpaRepository<CargoWarehouse, Integer> {
    List<CargoWarehouse> findAllByCargoIdAndRdtIsNull(Long cargoId);

    List<CargoWarehouse> findAllByWarehouseIdAndRdtIsNull(Long warehouseId);
}
