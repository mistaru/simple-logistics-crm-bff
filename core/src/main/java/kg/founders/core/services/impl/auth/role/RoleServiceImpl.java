package kg.founders.core.services.impl.auth.role;

import kg.founders.core.data_access_layer.repo.role.RoleRepo;
import kg.founders.core.entity.auth.permission.LogisticPermission;
import kg.founders.core.entity.auth.permission.LogisticRolePermission;
import kg.founders.core.entity.auth.role.LogisticRole;
import kg.founders.core.exceptions.ValidationException;
import kg.founders.core.model.auth.role.LogisticRoleModel;
import kg.founders.core.services.auth.role.RoleService;
import kg.founders.core.services.auth.role.permission.RolePermissionService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {
    RoleRepo repo;
    RolePermissionService rolePermissionService;

    @Override
    public List<LogisticRoleModel> listAllAsModel() {
        return repo.findByRdtIsNull().stream().map(LogisticRole::toModel).collect(Collectors.toList());
    }

    @Override
    public Optional<LogisticRole> findById(Long roleId) {
        return repo.findById(roleId);
    }

    @Override
    @Transactional
    public void save(LogisticRoleModel model) {
        model.validate();

        LogisticRole logisticRole;

        if (model.getId() == null) {
            logisticRole = new LogisticRole();
        } else {
            logisticRole = repo.getByIdAndRdtIsNull(model.getId())
                    .orElseThrow(() -> new ValidationException(
                            "Запись с id = " + model.getId() + " не найдена"));

            rolePermissionService.deleteAll(logisticRole.getLogisticRolePermissions());
            logisticRole.setLogisticRolePermissions(null);
        }

        logisticRole.setName(model.getName());
        logisticRole.setDescription(model.getDescription());

        repo.save(logisticRole);

        rolePermissionService.saveAll(
                model.getPermissions().stream()
                        .map(pm -> new LogisticRolePermission(
                                logisticRole,
                                new LogisticPermission(pm.getId()),
                                pm.getAccess()
                        ))
                        .collect(Collectors.toList()));
    }

    @Override
    public void delete(Long roleId) {
        repo.getByIdAndRdtIsNull(roleId)
                .ifPresent(role -> {
                    role.markDeleted();
                    role.setLogisticRolePermissions(null);
                    repo.save(role);
                });
    }
}
