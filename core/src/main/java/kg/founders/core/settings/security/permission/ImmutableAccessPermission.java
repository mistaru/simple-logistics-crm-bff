package kg.founders.core.settings.security.permission;

import lombok.Value;

@Value
public class ImmutableAccessPermission implements AccessPermission {
    int access;
}
