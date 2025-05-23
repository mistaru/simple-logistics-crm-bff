package kg.founders.core.services;

import kg.founders.core.model.CargoPaymentModel;
import kg.founders.core.model.PaymentModel;

import java.util.List;

public interface PaymentService {
    List<PaymentModel> getAll();

    List<PaymentModel> findAllByManagerId(Long aLong);

    PaymentModel getById(Long id);

    PaymentModel save(PaymentModel paymentModel);

    PaymentModel update(PaymentModel paymentModel);

    void delete(Long id);
    List<PaymentModel> getAllPaymentsByCargo(Long id);

    List<CargoPaymentModel> getAllCargoPayments();

    void reassign(Long managerId, Long paymentId);
}
