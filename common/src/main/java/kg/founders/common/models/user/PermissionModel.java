package kg.founders.common.models.user;

import kg.founders.common.enums.PermissionKey;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class PermissionModel {
    Long id;

    PermissionKey key;

    String description;
}
