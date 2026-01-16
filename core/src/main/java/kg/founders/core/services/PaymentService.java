package kg.founders.core.services;

import kg.founders.core.enums.PaymentStatus;
import kg.founders.core.model.CargoPaymentModel;
import kg.founders.core.model.PaymentModel;
import kg.founders.core.model.PaymentModelUpd;

import java.util.List;

public interface PaymentService {
    List<PaymentModel> getAll();

    List<PaymentModel> findAllByManagerId(Long aLong);

    PaymentModel getById(Long id);

    PaymentModel save(PaymentModel paymentModel);
    PaymentModel create(PaymentModel paymentModel);

    PaymentModel update(PaymentModelUpd paymentModel);

    void delete(Long id);

    List<PaymentModel> getAllPaymentsByCargo(Long id);

    List<CargoPaymentModel> getAllCargoPayments();

    void reassign(Long managerId, Long paymentId);

    List<PaymentModel> findAllByPayerIdsAndPaymentStatus(List<Long> payerIds, PaymentStatus paymentStatus);

    List<PaymentModel> findAllByPayerIdAndPaymentStatus(Long payerId, PaymentStatus paymentStatus);

}
