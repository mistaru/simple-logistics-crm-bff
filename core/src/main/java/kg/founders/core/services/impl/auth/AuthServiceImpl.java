package kg.founders.core.services.impl.auth;

import com.google.common.base.Strings;
import com.lambdaworks.crypto.SCryptUtil;
import kg.founders.core.data_access_layer.dao.AuthDao;
import kg.founders.core.entity.LogisticOldPassword;
import kg.founders.core.entity.auth.LogisticAuth;
import kg.founders.core.entity.auth.role.LogisticRole;
import kg.founders.core.exceptions.NotFoundException;
import kg.founders.core.exceptions.ValidationException;
import kg.founders.core.model.audit.AuditModel;
import kg.founders.core.model.auth.LogisticAuthModel;
import kg.founders.core.model.login.PasswordChangeModel;
import kg.founders.core.data_access_layer.repo.auth.AuthRepo;
import kg.founders.core.services.OldPasswordService;
import kg.founders.core.services.auth.AuthService;
import kg.founders.core.services.auth.role.AuthRoleService;
import kg.founders.core.services.auth.role.RoleService;
import kg.founders.core.util.ChainValidator;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AuthServiceImpl implements AuthService {
    AuthRepo repo;
    AuthDao dao;
    AuthRoleService authRoleService;
    RoleService roleService;

    OldPasswordService oldPasswordService;

    static Integer PASSWORD_EXPIRATION_DAYS = 30;

    @Override
    public Optional<LogisticAuth> findByUsername(String username) {
        return repo.findByUsername(username);
    }

    @Override
    public Optional<LogisticAuth> findById(Long authId) {
        return repo.findById(authId);
    }

    @Override
    public boolean blockAuth(Long authId, boolean block) {
        var auth = findById(authId).orElseThrow(() -> new NotFoundException(String.format("Учетная запись с id клиента %s не найдена", authId)));
        return blockAuth(auth.getUsername(), block);
    }

    @Override
    public boolean blockAuth(String username, boolean block) {
        return dao.blockAuth(username, block);
    }

    @Override
    @Transactional
    public void updatePassword(LogisticAuth logisticAuth, PasswordChangeModel model, AuditModel auditModel) {
        model.validate();

        if (Strings.isNullOrEmpty(logisticAuth.getPassword()) || !SCryptUtil.check(model.getCurrentPassword(), logisticAuth.getPassword())) {
            throw new ValidationException("Неверно указан текущий пароль");
        }

        oldPasswordService.save(new LogisticOldPassword(null, logisticAuth.getPassword(), logisticAuth));

        if (oldPasswordService.getLast5RowsByAuthId(logisticAuth.getId()).stream().anyMatch(
                oldPassword -> SCryptUtil.check(model.getNewPassword(), oldPassword.getPassword()))) {
            throw new ValidationException("Пароль не должен совпадать с 5 прeдыдущими!");
        }

        logisticAuth.setPassword(hashPassword(model.getNewPassword()));
        logisticAuth.addToTheExpireDateDays(PASSWORD_EXPIRATION_DAYS);

        save(logisticAuth);
    }

    @Override
    @Transactional
    public LogisticAuth save(LogisticAuthModel model) {
        model.validate();

        var logisticAuth = model.getId() == null ? new LogisticAuth() : findById(model.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Учетная запись с id клиента %s не найдена", model.getId())));

        List<LogisticRole> roles = model.getRoles()
                .stream()
                .map(roleModel -> roleService.findById(roleModel.getId())
                        .orElseThrow(ValidationException.fromMessage("Роль не существует")))
                .collect(Collectors.toList());

        logisticAuth.setUsername(model.getUsername());
        logisticAuth.setBlocked(model.getBlocked());
        if (model.getId() == null) {
            ChainValidator.create().thenPassword(model::getPassword).validate();
            logisticAuth.setPassword(hashPassword(model.getPassword()));
            logisticAuth.addToTheExpireDateDays(PASSWORD_EXPIRATION_DAYS);
        }
        var savedAuth = save(logisticAuth);

        savedAuth.setRoles(Set.copyOf(authRoleService.saveAllByAuthId(savedAuth, roles)));

        return savedAuth;
    }

    @Override
    public LogisticAuth save(LogisticAuth logisticAuth) {
        return repo.save(logisticAuth);
    }

    @Override
    public Boolean isBlocked(Long id) {
        return dao.isBlocked(id);
    }

    @Override
    public String hashPassword(String password) {
        return SCryptUtil.scrypt(password, 16, 16, 16);
    }

    @Override
    public LogisticAuthModel toModel(LogisticAuth logisticAuth) {
        return new LogisticAuthModel(
                logisticAuth.getId(),
                logisticAuth.getUsername(),
                null,
                logisticAuth.getBlocked(),
                logisticAuth.getLogisticAuthRoles().stream()
                        .map(authRole -> {
                            var roleModel = authRole.getLogisticRole().toModel();
                            roleModel.setActive(authRole.getActive());
                            return roleModel;
                        })
                        .collect(Collectors.toSet())
        );
    }

    @Override
    public void updateActiveRole(Long authId, Long roleId) {
        authRoleService.updateActive(authId, roleId);
    }

    @Override
    public List<LogisticAuthModel> findAll() {
        return repo.findAllByRdtIsNull().stream().map(this::toModel).collect(Collectors.toList());
    }

    @Override
    public void deleteByAuthId(Long authId) {
        var auth = findById(authId).orElseThrow(() -> new NotFoundException(String.format("Учетная запись с id клиента %s не найдена", authId)));
        auth.markDeleted();
        repo.save(auth);
    }
}
