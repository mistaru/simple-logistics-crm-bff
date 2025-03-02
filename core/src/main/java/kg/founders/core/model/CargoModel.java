package kg.founders.core.model;

import kg.founders.core.enums.CargoStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CargoModel {
    Long id;
    double weight;
    double volume;
    int quantity;
    Timestamp warehouseArrivalDate;
    Timestamp shipmentDate;
    ClientModel clientModel;
    CargoStatus status;
    String description;
}