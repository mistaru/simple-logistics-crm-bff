package kg.founders.core.model.login;

import kg.founders.core.util.ChainValidator;
import kg.founders.core.util.Validatable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PasswordChangeModel implements Validatable {
    String currentPassword;
    String newPassword;

    @Override
    public String validateMessage() {
        return ChainValidator.create()
                .thenNotEmpty(this::getCurrentPassword, "Укажите текущий пароль!")
                .thenPassword(this::getNewPassword)
                .validateMessage();
    }
}