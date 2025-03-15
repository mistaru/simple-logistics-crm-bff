package kg.founders.bff.config.settings.perms;

import kg.founders.bff.config.settings.TokenContextHolder;
import kg.founders.bff.config.settings.grant.GrantServiceImpl;
import kg.founders.core.enums.permission.AccessType;
import kg.founders.core.enums.permission.PermissionType;
import kg.founders.core.exceptions.BadRequestException;
import kg.founders.core.exceptions.ForbiddenException;
import kg.founders.core.model.audit.IdBased;
import kg.founders.core.settings.security.permission.ImmutableAccessPermission;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Map;
import java.util.function.Predicate;

public class PermissionValidation {

    public static void validateCreateUpdate(IdBased idBased) {
        if (idBased == null) throw new BadRequestException("Id required");

        var context = TokenContextHolder.currentOptional()
                .orElseThrow(ForbiddenException::new);
        var methodGrantHolder = context.getMethodGrantHolder();

        if (methodGrantHolder == null) throw new IllegalStateException(
                "Method have no permission annotations or marked with @ManualPermissionControl annotation"
        );

        Predicate<Map.Entry<PermissionType, ImmutableAccessPermission>> accessFilter;
        AccessType accessType;

        if (idBased.getId() == null) {
            accessFilter = entry -> entry.getValue().canCreate();
            accessType = AccessType.CREATE;
        } else {
            accessFilter = entry -> entry.getValue().canUpdate();
            accessType = AccessType.UPDATE;
        }

        var operationNotPermitted = methodGrantHolder.entrySet().stream()
                .filter(accessFilter)
                .map(entry -> new ImmutablePair<>(entry.getKey(), entry.getValue().getAccess() & accessType.getMask()))
                .noneMatch(entry -> GrantServiceImpl.getInstance()
                        .matchAll(
                                entry.getKey(),
                                entry.getValue()
                        )
                );

        if (operationNotPermitted) throw new ForbiddenException();
    }
}
