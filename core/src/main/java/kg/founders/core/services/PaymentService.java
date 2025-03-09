package kg.founders.core.services;

import kg.founders.core.model.PaymentModel;

import java.util.List;

public interface PaymentService {
    List<PaymentModel> getAll();

    PaymentModel getById(int id);

    PaymentModel save(PaymentModel paymentModel);

    PaymentModel update(PaymentModel paymentModel);

    void delete(Long id);

}
