package kg.founders.core.repo;

import kg.founders.core.entity.Truck;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Long> {

    @EntityGraph(attributePaths = "cargoTrucks")
    Optional<Truck> findWithCargoTrucksById(Long id);

    /**
     * Calculates the sum of 'serviceFee' for all trucks associated with a given carrier ID.
     * This represents the carrier's balance.
     *
     * @param carrierId The ID of the carrier.
     * @return The sum of service fees, or BigDecimal.ZERO if the carrier has no trucks.
     */
    @Query("SELECT COALESCE(SUM(t.serviceFee), 0) FROM Truck t WHERE t.carrier_id = :carrierId")
    BigDecimal calculateServiceFeeForCarrier(@Param("carrierId") Long carrierId);

    @Query("SELECT t.carrier.id as carrierId, SUM(t.serviceFee) as balance FROM Truck t WHERE t.carrier.id IN :carrierIds GROUP BY t.carrier.id")
    List<CarrierBalance> sumServiceFeeByCarrierIds(@Param("carrierIds") List<Long> carrierIds);

    default Map<Long, BigDecimal> getBalancesForCarriers(List<Long> carrierIds) {
        if (carrierIds == null || carrierIds.isEmpty()) {
            return Map.of();
        }
        return sumServiceFeeByCarrierIds(carrierIds).stream()
                .collect(Collectors.toMap(CarrierBalance::getCarrierId, CarrierBalance::getBalance));
    }

    interface CarrierBalance {
        Long getCarrierId();
        BigDecimal getBalance();
    }

}
