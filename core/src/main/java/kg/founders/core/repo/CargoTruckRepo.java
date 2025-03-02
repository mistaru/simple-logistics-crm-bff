package kg.founders.core.repo;

import kg.founders.core.entity.CargoTruck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoTruckRepo extends JpaRepository<CargoTruck, Integer> {
    List<CargoTruck> findByCargoId(Long cargoId);

    List<CargoTruck> findByTruckId(Long truckId);

}
