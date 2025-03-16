package kg.founders.core.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ClientModel {
    Long id;
    String fullName;
    String phoneNumber;
    String whatsappNumber;
    String email;
    String additionalInfo;
}