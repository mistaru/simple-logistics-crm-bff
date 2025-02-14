package kg.founders.core.entity.auth.permission;

import kg.founders.core.entity.auth.role.LogisticRole;
import kg.founders.core.enums.permission.AccessType;
import kg.founders.core.util.SqlTable;
import kg.founders.core.settings.security.permission.AccessPermission;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = LogisticRolePermission.TABLE_NAME)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class LogisticRolePermission implements AccessPermission {

    @SqlTable
    public static final String TABLE_NAME = "LOGISTIC_ROLE_PERMISSIONS";

    @EmbeddedId
    RolePermissionId id;

    @ManyToOne
    @MapsId("roleId")
    LogisticRole logisticRole;

    @ManyToOne
    @MapsId("permissionId")
    LogisticPermission logisticPermission;

    @Column(nullable = false)
    int permissionAccess;

    public LogisticRolePermission(LogisticRole logisticRole, LogisticPermission logisticPermission, int permissionAccess) {
        this.id = new RolePermissionId();
        this.logisticRole = logisticRole;
        this.logisticPermission = logisticPermission;
        this.permissionAccess = permissionAccess;
    }

    public void setPermissionAccess(int permissionAccess) {
        this.permissionAccess = permissionAccess & AccessType.ALL.getMask();
    }

    public GrantHolder toGrantHolder() {
        return new GrantHolder(logisticPermission.getName(), getPermissionAccess());
    }

    @Override
    public int getAccess() {
        return getPermissionAccess();
    }
}
