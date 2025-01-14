package kg.founders.common.models.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class RoleModel {
    Long id;

    String name;

    String description;

//    Set<UserModel> users;

    Set<RolePermissionModel> permissions;
}
