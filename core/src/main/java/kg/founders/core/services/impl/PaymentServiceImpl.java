package kg.founders.core.services.impl;

import kg.founders.core.converter.CargoModelToCargoPaymentModelConverter;
import kg.founders.core.converter.PaymentConverter;
import kg.founders.core.entity.Payment;
import kg.founders.core.model.CargoModel;
import kg.founders.core.model.CargoPaymentModel;
import kg.founders.core.model.PaymentModel;
import kg.founders.core.repo.PaymentRepo;
import kg.founders.core.services.CargoService;
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
    CargoModelToCargoPaymentModelConverter cargoModelToCargoPaymentModelConverter;

    CargoService cargoService;

    @Override
    public List<PaymentModel> getAll() {
        return repo.findAll().stream()
                .filter(payment -> payment.getRdt() == null)
                .map(converter::convertPaymentToPaymentModel).collect(Collectors.toList());
    }

    @Override
    public List<PaymentModel> findAllByManagerId(Long managerId) {
        return repo.findAllByManagerId(managerId).stream()
                .filter(payment -> payment.getRdt() == null)
                .map(converter::convertPaymentToPaymentModel).collect(Collectors.toList());
    }

    @Override
    public PaymentModel getById(Long id) {
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

    @Override
    public List<PaymentModel> getAllPaymentsByCargo(Long id) {
        return repo.findAllByCargoId(id).stream().map(converter::convertPaymentToPaymentModel).collect(Collectors.toList());
    }

    @Override
    public List<CargoPaymentModel> getAllCargoPayments() {
        List<CargoPaymentModel> cargoPaymentModels = cargoService.getAllActive()
                .stream()
                .map(cargoModelToCargoPaymentModelConverter::convertCargoToCargoPaymentModel)
                .collect(Collectors.toList()).stream().map(cargoPaymentModel -> {
                    cargoPaymentModel.setPaymentModelList(getAllPaymentsByCargo(cargoPaymentModel.getId()));
                    return cargoPaymentModel;
                }).collect(Collectors.toList());
        return cargoPaymentModels;
    }

    @Override
    public void reassign(Long managerId, Long paymentId) {
        Payment payment = repo.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
        payment.setManagerId(managerId);
        repo.save(payment);
    }
}
