package kg.founders.common.models.user;

import jakarta.validation.constraints.Email;
import kg.founders.common.models.BaseModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class UserModel extends BaseModel {
    String fullname;

    String username;

    @Email
    String email;

    String password;

    RoleModel role;

    String phoneNumber;
}
