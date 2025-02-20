package kg.founders.core.model.dictionaries;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CityDictModel extends BaseDictModel {
    Long id;
    CountryDictModel country;
}
