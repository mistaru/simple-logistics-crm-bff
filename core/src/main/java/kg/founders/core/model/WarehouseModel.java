package kg.founders.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.founders.core.model.dictionaries.CityDictModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class WarehouseModel {
    Long id;
    String name;
    @JsonProperty("isLocal")
    boolean isLocal;
    CityDictModel city;
    String address;
    String phoneNumber;
    BigDecimal volumeM3;
    BigDecimal weightKg;

    double occupiedVolume;
    double occupiedWeight;
}