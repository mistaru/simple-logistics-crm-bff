package kg.founders.bff.controller.permission;

import kg.founders.core.model.auth.role.permission.LogisticPermissionModel;
import kg.founders.core.services.auth.role.permission.PermissionService;
import kg.founders.core.settings.security.permission.annotation.ManualPermissionControl;
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
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PermissionControllerRest {

    PermissionService permissionsService;

    @GetMapping
    @ManualPermissionControl
    public List<LogisticPermissionModel> list() {
        return permissionsService.listAllAsModel();
    }
}