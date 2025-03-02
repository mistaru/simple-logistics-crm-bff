package kg.founders.core.enums;

public enum CargoStatus {
    NEW,                    // Новый
    PRICING,                // Прайсинг
    PROCESSED,              // Пройсед
    ASSEMBLY_AT_FACTORY,    // В процессе сборки на заводе
    IN_TRANSIT_TO_WAREHOUSE,// В пути с завода до склада
    AT_LOCAL_WAREHOUSE,     // На локальном складе
    IN_TRANSIT_TO_CITY,     // В пути до города назначения
    AT_DESTINATION_WAREHOUSE, // На складе города назначения
    DELIVERED_TO_CLIENT     // Отгружен клиенту

}
