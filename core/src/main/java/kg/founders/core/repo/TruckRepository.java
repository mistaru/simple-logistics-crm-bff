package kg.founders.core.repo;

import kg.founders.core.entity.Truck;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Long> {

    @EntityGraph(attributePaths = "cargoTrucks")
    Optional<Truck> findWithCargoTrucksById(Long id);

}
