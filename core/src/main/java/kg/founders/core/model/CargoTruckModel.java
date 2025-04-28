package kg.founders.core.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class CargoTruckModel {

    TruckModel truck;
    List<CargoModel> cargos;

}
