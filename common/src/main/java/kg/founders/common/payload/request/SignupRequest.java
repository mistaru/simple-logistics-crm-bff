package kg.founders.common.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    String username;
    @NotBlank
    @Size(max = 50)
    @Email
    String email;
    String role;
    @NotBlank
    @Size(min = 6, max = 40)
    String password;
}
