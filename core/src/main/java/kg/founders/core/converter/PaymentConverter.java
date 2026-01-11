package kg.founders.core.converter;

import kg.founders.core.entity.Payment;
import kg.founders.core.model.PaymentModel;
import kg.founders.core.model.PaymentModelUpd;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
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
        paymentModel.setActual(payment.getActual());
        paymentModel.setComment(payment.getComment());
        paymentModel.setManagerId(payment.getManagerId());
        paymentModel.setPayer_id(payment.getPayer_id());
        paymentModel.setAmount(payment.getAmount());
        paymentModel.setType(payment.getStatus().name());
        return paymentModel;
    }

    public Payment convertPaymentModelToPayment(PaymentModel paymentModel) {
        Payment payment = new Payment();
        payment.setId(paymentModel.getId());
        payment.setStatus(paymentModel.getStatus());
        payment.setActual(paymentModel.getActual());
        payment.setPayer_id(paymentModel.getPayer_id());
        payment.setComment(paymentModel.getComment());
        payment.setManagerId(paymentModel.getManagerId());
        payment.setAmount(paymentModel.getAmount());
        return payment;
    }

    public Payment convertPaymentModelToPaymentUpd(PaymentModelUpd paymentModel) {
        Payment payment = new Payment();
        payment.setId(paymentModel.getId());
        payment.setActual(paymentModel.getActual());
        payment.setPayer_id(paymentModel.getPayer_id());
        payment.setComment(paymentModel.getComment());
        payment.setManagerId(paymentModel.getManagerId());
        payment.setAmount(paymentModel.getAmount());
        return payment;
    }
}
