package kg.founders.core.model.auth;

import kg.founders.core.model.audit.IdBased;
import kg.founders.core.model.auth.role.LogisticRoleModel;
import kg.founders.core.util.ChainValidator;
import kg.founders.core.util.Validatable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LogisticAuthModel implements IdBased, Validatable {
    Long id;
    String username;
    String password;
    Timestamp blocked;
    Set<LogisticRoleModel> roles;

    @Override
    public String validateMessage() {
        return ChainValidator.create()
                .thenNotEmpty(this::getUsername, "Логин не указан")
                .thenMatch(
                        this::getUsername,
                        "^[a-zA-Z0-9_]{6,20}$",
                        "Логин должен содержать от 6 до 20 символов, только латинские символы и цифры"
                )
                .validateMessage();
    }
}
