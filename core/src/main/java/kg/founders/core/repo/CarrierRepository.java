package kg.founders.core.repo;

import kg.founders.core.entity.Carrier;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarrierRepository extends JpaRepository<Carrier, Long> {

    @EntityGraph(attributePaths = "trucks")
    @Query("SELECT c FROM Carrier c WHERE c.rdt IS NULL")
    List<Carrier> findAllWithTrucks();

    @EntityGraph(attributePaths = "trucks")
    @Query("SELECT c FROM Carrier c WHERE c.id = :id AND c.rdt IS NULL")
    Optional<Carrier> findWithTrucksById(@Param("id") Long id);

    @Query("select t.id from Carrier t where t.rdt is null")
    List<Long> findIdByRdtIsNull();

    @Modifying
    @Query("UPDATE Carrier c SET c.balance = :amount WHERE c.id = :id")
    void updateCarrierBalance(@Param("id") Long id, @Param("amount") BigDecimal amount);

}