package kg.founders.core.enums;

import kg.founders.core.model.EnumModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum CargoStatus {
    NEW("Новый"),
    PRICING("Прайсинг"),
    PROCESSED("Прайсед"),
    ASSEMBLY_AT_FACTORY("В процессе сборки на заводе"),
    IN_TRANSIT_TO_WAREHOUSE("В пути с завода до склада"),
    AT_LOCAL_WAREHOUSE("На локальном складе"),
    IN_TRANSIT_TO_CITY("В пути до города назначения"),
    AT_DESTINATION_WAREHOUSE("На складе города назначения"),
    DELIVERED_TO_CLIENT("Отгружен клиенту"),
    ;

    final String description;


    public static List<EnumModel> getAllToModel() {
        return Arrays.stream(CargoStatus.values())
                .map(status -> new EnumModel(status.name(), status.getDescription()))
                .collect(Collectors.toList());
    }

}
