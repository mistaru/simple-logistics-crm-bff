package kg.founders.core.model;

import kg.founders.core.enums.CargoStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CargoPaymentModel {
    Long id;
    String clientFullName;
    CargoStatus status;
    Timestamp warehouseArrivalDate;
    Timestamp shipmentDate;
    String description;

    List<PaymentModel> paymentModelList;

}
