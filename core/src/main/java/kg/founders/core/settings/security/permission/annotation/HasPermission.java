package kg.founders.core.settings.security.permission.annotation;

import kg.founders.core.enums.permission.AccessType;
import kg.founders.core.enums.permission.PermissionType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface HasPermission {

    PermissionType value();

    AccessType[] access() default {AccessType.ALL};
}
