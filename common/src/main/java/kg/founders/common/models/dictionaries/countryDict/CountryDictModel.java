package kg.founders.common.models.dictionaries.countryDict;

import kg.founders.common.models.BaseModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class CountryDictModel extends BaseModel {
    String name;

    String description;
}
