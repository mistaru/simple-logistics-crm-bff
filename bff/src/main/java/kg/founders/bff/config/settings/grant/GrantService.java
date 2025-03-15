package kg.founders.bff.config.settings.grant;

import kg.founders.core.enums.permission.AccessType;
import kg.founders.core.enums.permission.PermissionType;

import java.util.EnumSet;

public interface GrantService {

    boolean matchAny(PermissionType permission, int accessTypeMask);

    boolean matchAll(PermissionType permission, int accessTypeMask);

    boolean hasAny(PermissionType permission, AccessType... accessTypes);

    boolean hasAny(AccessType accessType, EnumSet<PermissionType> permissions);

    boolean hasAny(AccessType accessType, PermissionType... permissions);

    void checkHasAny(PermissionType permission, AccessType... accessTypes);

    void checkHasAny(AccessType accessType, PermissionType... permissions);

    void checkHasAny(AccessType accessType, EnumSet<PermissionType> permissions);
}