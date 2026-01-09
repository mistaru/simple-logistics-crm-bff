package kg.founders.core.enums;

import kg.founders.core.model.EnumModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum PaymentStatus {

    CLIENT_PAYS_FOR_CARGO("Клиент оплачивает за груз"),
    COMPANY_PAYS_CARRIERS("Компания оплачивает перевозчикам");

    String value;

    @Override
    public String toString() {
        return value;
    }

    public static List<EnumModel> getAllToModel() {
        return Arrays.stream(PaymentStatus.values())
                .map(status -> new EnumModel(status.name(), status.toString()))
                .collect(Collectors.toList());
    }
}
