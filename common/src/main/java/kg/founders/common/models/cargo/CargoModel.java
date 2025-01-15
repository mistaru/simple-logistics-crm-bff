package kg.founders.common.models.cargo;

import kg.founders.common.models.BaseModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class CargoModel extends BaseModel {
    String description;
    Double weight;
    Double volume;
    Integer unitCount;
    LocalDateTime arrivalDate;
    LocalDateTime departureDate;
    List<String> deliveryRoutes;
    Long clientId;
}
