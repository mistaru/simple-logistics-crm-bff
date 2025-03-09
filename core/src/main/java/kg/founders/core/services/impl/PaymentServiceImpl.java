package kg.founders.core.services.impl;

import kg.founders.core.converter.PaymentConverter;
import kg.founders.core.entity.Payment;
import kg.founders.core.model.PaymentModel;
import kg.founders.core.repo.PaymentRepo;
import kg.founders.core.services.PaymentService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PaymentServiceImpl implements PaymentService {

    PaymentRepo repo;
    PaymentConverter converter;

    @Override
    public List<PaymentModel> getAll() {
        return repo.findAll().stream()
                .filter(payment -> payment.getRdt() == null)
                .map(converter::convertPaymentToPaymentModel).collect(Collectors.toList());
    }

    @Override
    public PaymentModel getById(int id) {
        return converter.convertPaymentToPaymentModel(repo.findById((long) id).orElse(null));
    }

    @Override
    public PaymentModel save(PaymentModel paymentModel) {
        Payment payment = converter.convertPaymentModelToPayment(paymentModel);
        repo.save(payment);
        return converter.convertPaymentToPaymentModel(payment);
    }

    @Override
    public PaymentModel update(PaymentModel paymentModel) {
        Optional<Payment> oldPayment = repo.findById(paymentModel.getId());
        Payment newPayment = converter.convertPaymentModelToPayment(paymentModel);
        newPayment.setId(oldPayment.get().getId());
        newPayment.setMdt(new Timestamp(System.currentTimeMillis()));
        repo.save(newPayment);

        return converter.convertPaymentToPaymentModel(newPayment);
    }

    @Override
    public void delete(Long id) {
        Optional<Payment> payment = repo.findById(id);
        if (payment.isPresent()) {
            payment.get().markDeleted();
            repo.save(payment.get());
        }
    }


}
