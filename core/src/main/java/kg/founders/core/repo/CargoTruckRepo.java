package kg.founders.core.repo;

import kg.founders.core.entity.CargoTruck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoTruckRepo extends JpaRepository<CargoTruck, Long> {
    List<CargoTruck> findAllByCargoIdAndRdtIsNull(Long cargoId);

    List<CargoTruck> findAllByTruckIdAndRdtIsNull(Long truckId);

    List<CargoTruck> findAllByCargoIdOrTruckId(Long cargoId, Long truckId);

}
