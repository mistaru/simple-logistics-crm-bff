package kg.founders.core.converter;

import kg.founders.core.entity.Payment;
import kg.founders.core.model.PaymentModel;

import javax.annotation.PostConstruct;

public class PaymentConverter extends ModelConverter<PaymentModel, Payment> {

    @PostConstruct
    public void init() {
        this.fromEntity = this::convertPaymentToPaymentModel;
        this.fromModel = this::convertPaymentModelToPayment;
    }

    public PaymentModel convertPaymentToPaymentModel(Payment payment) {
        if (payment == null) return null;
        PaymentModel paymentModel = new PaymentModel();
        paymentModel.setId(payment.getId());
        paymentModel.setStatus(payment.getStatus());
        paymentModel.setCargo(payment.getCargo());
        paymentModel.setPlanned(payment.getPlanned());
        paymentModel.setActual(payment.getActual());
        paymentModel.setComment(payment.getComment());
        return paymentModel;
    }

    public Payment convertPaymentModelToPayment(PaymentModel paymentModel) {
        Payment payment = new Payment();
        payment.setId(paymentModel.getId());
        payment.setStatus(paymentModel.getStatus());
        payment.setCargo(paymentModel.getCargo());
        payment.setPlanned(paymentModel.getPlanned());
        payment.setActual(paymentModel.getActual());
        payment.setComment(paymentModel.getComment());
        return payment;
    }
}
