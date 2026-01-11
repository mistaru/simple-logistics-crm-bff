package kg.founders.core.model;

import kg.founders.core.enums.PaymentStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class PaymentModel {
    Long id;
    PaymentStatus status;
    Timestamp actual;
    String comment;
    Long managerId;
    Long payer_id;
    BigDecimal amount;
}