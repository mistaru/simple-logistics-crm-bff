package kg.founders.core.enums.permission;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public enum AccessType {
    CREATE(0b1000),
    READ(0b0100),
    UPDATE(0b0010),
    DELETE(0b0001),
    ALL(0b1111),
    ;

    int mask;

    public static int combine(AccessType... access) {
        int r = 0;
        for (var accessType : access) r |= accessType.mask;
        return r;
    }
}
