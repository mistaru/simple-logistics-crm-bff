package kg.founders.core.settings.security.permission;


import kg.founders.core.enums.permission.AccessType;

public interface AccessPermission {

    int getAccess();

    default boolean hasAll(int mask) {
        mask = mask & AccessType.ALL.getMask();
        return (getAccess() & mask) == mask;
    }

    default boolean hasAny(int mask) {
        mask = mask & AccessType.ALL.getMask();
        return (getAccess() & mask) > 0;
    }

    default boolean hasAny(AccessType... accessTypes) {
        if (accessTypes.length == 0) return false;
        var access = getAccess();
        for (var accessType : accessTypes) {
            if ((access & accessType.getMask()) == accessType.getMask()) return true;
        }
        return false;
    }

    default boolean hasAll(AccessType... accessTypes) {
        if (accessTypes.length == 0) return false;
        var access = getAccess();
        for (var accessType : accessTypes) {
            if ((access & accessType.getMask()) != accessType.getMask()) return false;
        }
        return true;
    }

    default boolean canCreate() {
        return hasAny(AccessType.CREATE);
    }

    default boolean canRead() {
        return hasAny(AccessType.READ);
    }

    default boolean canUpdate() {
        return hasAny(AccessType.UPDATE);
    }

    default boolean canDelete() {
        return hasAny(AccessType.DELETE);
    }

    default boolean canAnything() {
        return hasAny(AccessType.ALL);
    }

    default boolean canSomething() {
        return (getAccess() & AccessType.ALL.getMask()) > 0;
    }
}
