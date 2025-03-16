package kg.founders.bff.controller.menu;

import kg.founders.bff.config.settings.TokenContextHolder;
import kg.founders.bff.config.settings.grant.GrantService;
import kg.founders.bff.model.PermissionGroup;
import kg.founders.core.entity.auth.permission.GrantHolder;
import kg.founders.core.enums.permission.AccessType;
import kg.founders.core.enums.permission.PermissionType;
import kg.founders.core.settings.security.permission.annotation.ManualPermissionControl;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MenuControllerRest {

    GrantService grant;

    @GetMapping("/api/menu")
    @ManualPermissionControl
    public List<PermissionGroup> menu() {
        return TokenContextHolder.current().getPrincipal()
                .getAuthorities()
                .stream()
                .filter(gh -> gh.hasAny(AccessType.READ) &&
                        gh.getPermission().getView() != null)
                .map(GrantHolder::getPermission)
                .collect(Collectors.groupingBy(PermissionType::getScreenType))
                .entrySet()
                .stream()
                .map(entry -> new PermissionGroup(
                                entry.getKey(),
                                entry.getValue()
                                        .stream()
                                        .sorted(Comparator.comparingInt(Enum::ordinal))
                                        .collect(Collectors.toList())
                        )
                )
                .sorted(Comparator.comparingInt(o -> o.getScreen().ordinal()))
                .collect(Collectors.toList());
    }
}