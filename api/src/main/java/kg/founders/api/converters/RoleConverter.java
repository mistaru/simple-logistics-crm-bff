package kg.founders.api.converters;

import jakarta.annotation.PostConstruct;
import kg.founders.api.repositories.RoleRepository;
import kg.founders.common.converters.ModelConverter;
import kg.founders.common.entities.user.Role;
import kg.founders.common.models.user.RoleModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleConverter extends ModelConverter<RoleModel, Role> {
//    private final UserConverter userConverter;
    private final RolePermissionConverter rolePermissionConverter;
    private final RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        this.fromEntity = this::convertToModel;
        this.fromModel = this::convertToEntity;
    }

    private Role convertToEntity(RoleModel model) {
        Role entity = roleRepository.findById(model.getId()).get();
        //todo need to finish
//        Role entity = new Role();
//        entity.setId(model.getId());
//        entity.setName(model.getName());
//        entity.setDescription(model.getDescription());
////        entity.setUsers(model.getUsers().stream()
////                .map(userConverter::convertFromModel)
////                .collect(Collectors.toSet()));
//        entity.setPermissions(model.getPermissions().stream()
//                .map(rolePermissionConverter::convertFromModel)
//                .collect(Collectors.toSet()));
        return entity;
    }

    private RoleModel convertToModel(Role entity) {
        if (entity == null) return null;
        RoleModel model = new RoleModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        //todo need to finish
//        model.setUsers(entity.getUsers().stream()
//                .map(userConverter::convertFromEntity)
//                .collect(Collectors.toSet()));
        model.setPermissions(entity.getPermissions().stream()
                .map(rolePermissionConverter::convertFromEntity)
                .collect(Collectors.toSet()));
        return model;
    }
}
