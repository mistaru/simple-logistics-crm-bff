package kg.founders.common.models.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class RolePermissionModel {
    Long id;

//    RoleModel role;

    PermissionModel permission;
}
