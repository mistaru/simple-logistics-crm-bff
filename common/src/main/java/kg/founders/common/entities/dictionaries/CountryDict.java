package kg.founders.common.entities.dictionaries;

import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "country_dict")
@SequenceGenerator(name = "id_generator", sequenceName = "country_dict_id_seq", allocationSize = 1)
public class CountryDict extends BaseDict {
}
