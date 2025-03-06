package kg.founders.core.enums.permission;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum PermissionType implements GrantedAuthority {
    TEST("", ScreenType.GENERAL, "", ""),
    AUTH("Пользователи", ScreenType.ADMINISTRATION, "auth", "folder_shared"),
    PERMISSION("Просмотр доступов", ScreenType.ADMINISTRATION, "permission", "people_alt"),
    ROLE("Редактирование ролей", ScreenType.ADMINISTRATION, "roles", "person_add");

    String description;
    ScreenType screenType;
    String view;
    String icon;

    public static PermissionType fromName(String name) {
        return name == null ? null : valueOf(name);
    }

    @Override
    public String getAuthority() {
        return name();
    }


    public static ScreenType screenFromPermission(PermissionType perm) {
        return mappingCache.entrySet().stream()
                .filter(entry -> entry.getValue().contains(perm))
                .map(Map.Entry::getKey)
                .findAny()
                .orElseThrow();
    }

    private static final EnumMap<ScreenType, EnumSet<PermissionType>> mappingCache;

    static {
        mappingCache = new EnumMap<>(ScreenType.class);

        Stream.of(values())
                .forEach(perm -> mappingCache.compute(
                        perm.getScreenType(),
                        (screenType, permissionTypes) -> {
                            if (permissionTypes == null) {
                                return EnumSet.of(perm);
                            } else {
                                permissionTypes.add(perm);
                                return permissionTypes;
                            }
                        }
                ));
    }
}