package kg.founders.api.converters;

import jakarta.annotation.PostConstruct;
import kg.founders.common.converters.ModelConverter;
import kg.founders.common.entities.user.RolePermission;
import kg.founders.common.models.user.RolePermissionModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RolePermissionConverter extends ModelConverter<RolePermissionModel, RolePermission> {
//    private final RoleConverter roleConverter;
    private final PermissionConverter permissionConverter;

    @PostConstruct
    public void init() {
        this.fromEntity = this::convertToModel;
        this.fromModel = this::convertToEntity;
    }

    private RolePermission convertToEntity(RolePermissionModel model) {
        RolePermission entity = new RolePermission();
        entity.setId(model.getId());
//        entity.setRole(roleConverter.convertFromModel(model.getRole()));
        entity.setPermission(permissionConverter.convertFromModel(model.getPermission()));
        return entity;
    }

    private RolePermissionModel convertToModel(RolePermission entity) {
        if (entity == null) return null;
        RolePermissionModel model = new RolePermissionModel();
        model.setId(entity.getId());
//        model.setRole(roleConverter.convertFromEntity(entity.getRole()));
        model.setPermission(permissionConverter.convertFromEntity(entity.getPermission()));
        return model;
    }
}
