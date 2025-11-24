package kg.founders.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.founders.core.model.dictionaries.CityDictModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WarehouseModel {
    Long id;
    String name;
    CityDictModel city;
    String address;
    String phoneNumber;
    BigDecimal volumeM3;
    BigDecimal weightKg;
    @JsonProperty("isLocal")
    boolean isLocal;
}