package kg.founders.core.entity.dictionaries;

import kg.founders.core.entity.BaseEntity;
import lombok.*;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseDict extends BaseEntity {

    protected String name;

    protected String description;
}
