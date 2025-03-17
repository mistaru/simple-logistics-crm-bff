package kg.founders.core.enums.permission;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public enum ScreenType {
    GENERAL(null, ""),
    ADMINISTRATION("Безопасность", "mdi-badge-account-horizontal"),
    ;

    String description;
    String icon;

    public static ScreenType fromName(String name) {
        return name == null ? null : valueOf(name);
    }
}
