package kg.founders.core.repo;

import kg.founders.core.entity.Payment;
import kg.founders.core.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {
    List<Payment> findAllByCargoId(Long cargoId);
    List<Payment> findAllByManagerId(Long managerId);
    List<Payment> findAllByPayerIdInAndStatusAndRdtIsNull(List<Long> payerIds, PaymentStatus status);
    List<Payment> findAllByCargoIdInAndStatusAndRdtIsNull(List<Long> cargoIds, PaymentStatus status);
    List<Payment> findAllByPayerIdAndStatusAndRdtIsNull(Long payerId, PaymentStatus status);
}
