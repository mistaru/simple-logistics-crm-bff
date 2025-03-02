package kg.founders.core.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class TruckModel {

    Long id;
    String registrationCountry;
    double volumeM3;
    String departureWarehouse;
    String deliveryWarehouse;
    String driverPhone;
    String additionalInformation;
}
