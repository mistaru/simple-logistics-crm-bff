package kg.founders.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.founders.core.enums.CargoStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CargoProfileModel {
    Long id;

    double weight;
    double volume;
    int quantity;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    Timestamp warehouseArrivalDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    Timestamp shipmentDate;
    CargoStatus status;
    String description;
    Long managerId;
    BigDecimal price;
    BigDecimal invoiceTotal;
    BigDecimal paymentReceived;
    BigDecimal balanceDue;
}