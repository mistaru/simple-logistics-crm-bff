package kg.founders.core.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class TruckModel {

    Long id;
    String registrationCountry;
    double volumeTotalM3;
    double volumeOccupiedM3;
    double volumeAvailableM3;
    String departureWarehouse;
    String arrivalWarehouse;
    String driverFullname;
    String driverPhone;
    Timestamp departureDatePlanned;
    Timestamp departureDateActual;
    Timestamp arrivalDatePlanned;
    Timestamp arrivalDateActual;
    String additionalInformation;

}