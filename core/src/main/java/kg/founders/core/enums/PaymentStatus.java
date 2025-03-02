package kg.founders.core.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum PaymentStatus {

    NEW("Новый"),
    APPROVED("Одобрен"),
    CANCELED("Отменён");

    String value;

    @Override
    public String toString() {
        return value;
    }
}
