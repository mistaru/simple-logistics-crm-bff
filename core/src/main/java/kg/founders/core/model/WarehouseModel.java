package kg.founders.core.model;

import kg.founders.core.enums.WarehouseType;
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
    WarehouseType warehouseType;
    CityDictModel city;
    String address;
    String phoneNumber;
    BigDecimal volumeM3;
}