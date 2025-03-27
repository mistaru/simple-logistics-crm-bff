package kg.founders.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    Timestamp warehouseArrivalDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    Timestamp shipmentDate;
    ClientModel client;
    CargoStatus status;
    String description;
}