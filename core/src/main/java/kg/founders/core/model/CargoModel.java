package kg.founders.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.founders.core.enums.CargoStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CargoModel {
    Long id;
    double weight;
    double volume;
    int quantity;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    Timestamp warehouseArrivalDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    Timestamp shipmentDate;
    ClientModel clientModel;
    CargoStatus status;
    String description;
}