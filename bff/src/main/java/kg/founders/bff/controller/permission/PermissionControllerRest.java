package kg.founders.bff.controller.permission;

import kg.founders.core.enums.permission.AccessType;
import kg.founders.core.enums.permission.PermissionType;
import kg.founders.core.model.auth.role.permission.LogisticPermissionModel;
import kg.founders.core.services.auth.role.permission.PermissionService;
import kg.founders.core.settings.security.permission.annotation.HasAccess;
import kg.founders.core.settings.security.permission.annotation.HasPermission;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@RestController
@RequestMapping("/api/permission")
@AllArgsConstructor(access = PACKAGE)
@HasPermission(value = PermissionType.PERMISSION)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PermissionControllerRest {

    PermissionService permissionsService;

    @GetMapping
    @HasAccess(AccessType.READ)
    public List<LogisticPermissionModel> list() {
        return permissionsService.listAllAsModel();
    }
}