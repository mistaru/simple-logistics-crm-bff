package kg.founders.core.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class CarrierProfileModel extends CarrierModel {

    BigDecimal totalInvoiceTotal;
    BigDecimal totalPaymentReceived;
    BigDecimal totalBalanceDue;

    public CarrierProfileModel(CarrierModel carrierModel) {
        if (carrierModel != null) {
            this.setId(carrierModel.getId());
            this.setName(carrierModel.getName());
            this.setEmail(carrierModel.getEmail());
            this.setPhoneNumber(carrierModel.getPhoneNumber());
            this.setBalance(carrierModel.getBalance());
            this.setTrucks(carrierModel.getTrucks());
        }
    }

}
