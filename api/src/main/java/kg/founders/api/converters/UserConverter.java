package kg.founders.api.converters;

import jakarta.annotation.PostConstruct;
import kg.founders.common.converters.ModelConverter;
import kg.founders.common.entities.user.User;
import kg.founders.common.models.user.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverter extends ModelConverter<UserModel, User> {
    private final RoleConverter roleConverter;

    @PostConstruct
    public void init() {
        this.fromEntity = this::convertToModel;
        this.fromModel = this::convertToEntity;
    }

    private User convertToEntity(UserModel model) {
        User entity = new User();
        entity.setId(model.getId());
        entity.setCreatedAt(model.getCreatedAt());
        entity.setUpdatedAt(model.getUpdatedAt());
        entity.setFullname(model.getFullname());
        entity.setUsername(model.getUsername());
        entity.setEmail(model.getEmail());
        entity.setPassword(model.getPassword());
        entity.setRole(roleConverter.convertFromModel(model.getRole()));
        return entity;
    }

    private UserModel convertToModel(User entity) {
        if (entity == null) return null;
        UserModel model = new UserModel();
        model.setId(entity.getId());
        model.setCreatedAt(entity.getCreatedAt());
        model.setUpdatedAt(entity.getUpdatedAt());
        model.setUsername(entity.getUsername());
        model.setFullname(entity.getFullname());
        model.setEmail(entity.getEmail());
//        model.setPassword();
        model.setRole(roleConverter.convertFromEntity(entity.getRole()));
        return model;
    }
}
