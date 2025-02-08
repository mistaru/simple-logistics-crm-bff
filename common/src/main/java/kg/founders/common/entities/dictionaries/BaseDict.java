package kg.founders.common.entities.dictionaries;

import jakarta.persistence.MappedSuperclass;
import kg.founders.common.entities.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseDict extends BaseEntity {

    protected String name;

    protected String description;
}
