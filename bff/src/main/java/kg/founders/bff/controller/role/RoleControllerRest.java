package kg.founders.bff.controller.role;

import kg.founders.bff.config.settings.perms.PermissionValidation;
import kg.founders.core.enums.permission.AccessType;
import kg.founders.core.model.auth.role.LogisticRoleModel;
import kg.founders.core.services.auth.role.RoleService;
import kg.founders.core.settings.security.permission.annotation.HasAccess;
import kg.founders.core.settings.security.permission.annotation.ManualPermissionControl;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/role")
//@HasPermission(PermissionType.ROLE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleControllerRest {

    RoleService roleService;

    @GetMapping
    @ManualPermissionControl
    public List<LogisticRoleModel> list() {
        return roleService.listAllAsModel();
    }


    @PostMapping
    @HasAccess({AccessType.CREATE, AccessType.UPDATE})
    public void save(@RequestBody LogisticRoleModel logisticRoleModel) {
        PermissionValidation.validateCreateUpdate(logisticRoleModel);

        roleService.save(logisticRoleModel);
    }

    @DeleteMapping()
    @HasAccess(AccessType.DELETE)
    public void delete(@RequestParam Long roleId) {
        roleService.delete(roleId);
    }
}