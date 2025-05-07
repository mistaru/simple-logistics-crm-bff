package kg.founders.core.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class PriceModel {
    Long id;
    BigDecimal amount;
    CargoModel cargo;
}