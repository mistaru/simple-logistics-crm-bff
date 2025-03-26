package kg.founders.core.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientModel {
    Long id;
    String fullName;
    String clientCode;
    String phoneNumber;
    String whatsappNumber;
    String email;
    String additionalInfo;
}