package kg.founders.core.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ClientModel {
    Long id;
    String fullName;
    String phoneNumber;
    String whatsappNumber;
    String email;
    String additionalInfo;
}