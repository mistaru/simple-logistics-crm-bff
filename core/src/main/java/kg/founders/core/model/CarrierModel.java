package kg.founders.core.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class CarrierModel {

    Long id;
    String name;
    String email;
    String phoneNumber;
    BigDecimal balance;
    List<TruckModel> trucks;

}
