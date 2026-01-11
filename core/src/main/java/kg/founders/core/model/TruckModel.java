package kg.founders.core.model;

import kg.founders.core.enums.CargoStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class TruckModel {

    Long id;
    String registrationCountry;
    Double volumeTotalM3;
    Double volumeOccupiedM3;
    Double volumeAvailableM3;
    String departureWarehouse;
    String arrivalWarehouse;
    String driverFullname;
    String driverPhone;
    Timestamp departureDatePlanned;
    Timestamp departureDateActual;
    Timestamp arrivalDatePlanned;
    Timestamp arrivalDateActual;
    String additionalInformation;
    CarrierModel carrier;
    BigDecimal  serviceFee;
    BigDecimal customsFee;
    BigDecimal expenses;
    BigDecimal additionalExpenses;
    BigDecimal totalAmount;
    CargoStatus cargoStatus;

}