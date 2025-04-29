package kg.founders.core.model;

import kg.founders.core.entity.Cargo;
import kg.founders.core.enums.PaymentStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class PaymentModel {
    Long id;

    PaymentStatus status;

    Cargo cargo;

    Timestamp planned;

    Timestamp actual;

    String comment;

    Long managerId;
}
