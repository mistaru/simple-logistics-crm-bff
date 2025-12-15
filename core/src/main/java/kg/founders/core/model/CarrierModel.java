package kg.founders.core.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

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

}
