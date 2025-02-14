package kg.founders.core.model.auth.role;


import kg.founders.core.model.audit.IdBased;
import kg.founders.core.model.auth.role.permission.LogisticPermissionModel;
import kg.founders.core.util.ChainValidator;
import kg.founders.core.util.Validatable;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LogisticRoleModel implements Serializable, Validatable, IdBased {
    Long id;
    String name;
    String description;
    boolean active;
    List<LogisticPermissionModel> permissions;

    @Override
    public String validateMessage() {
        return ChainValidator.create()
                .then(id)
                .thenNotEmpty(this::getName, "Название роли не указано")
                .then(() -> {
                    var permissions = getPermissions();
                    if (permissions == null || permissions.isEmpty()) return "Не указано ни одного разрешения для роли";
                    return permissions.stream()
                            .map(perm -> {
                                var validateId = Validatable.validateId(perm.getId());
                                if (validateId == null) {
                                    return perm.canSomething() ? null : "Не указано ни одного типа доступа для разрешения";
                                } else {
                                    return validateId;
                                }
                            })
                            .filter(Objects::nonNull)
                            .findAny().orElse(null);
                })
                .validateMessage();
    }
}