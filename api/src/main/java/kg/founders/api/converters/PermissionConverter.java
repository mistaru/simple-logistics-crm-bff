package kg.founders.api.converters;

import jakarta.annotation.PostConstruct;
import kg.founders.common.converters.ModelConverter;
import kg.founders.common.entities.user.Permission;
import kg.founders.common.models.user.PermissionModel;
import org.springframework.stereotype.Component;

@Component
public class PermissionConverter extends ModelConverter<PermissionModel, Permission> {

    @PostConstruct
    public void init() {
        this.fromEntity = this::convertToModel;
        this.fromModel = this::convertToEntity;
    }

    private Permission convertToEntity(PermissionModel model) {
        Permission entity = new Permission();
        entity.setId(model.getId());
        entity.setKey(model.getKey());
        entity.setDescription(model.getDescription());
        return entity;
    }

    private PermissionModel convertToModel(Permission entity) {
        if (entity == null) return null;
        PermissionModel model = new PermissionModel();
        model.setId(entity.getId());
        model.setKey(entity.getKey());
        model.setDescription(entity.getDescription());
        return model;
    }
}
