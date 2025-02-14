package kg.founders.core.entity.auth.permission;

import kg.founders.core.entity.BaseEntity;
import kg.founders.core.enums.permission.PermissionType;
import kg.founders.core.model.audit.IdBased;
import kg.founders.core.util.SqlTable;
import kg.founders.core.model.auth.role.permission.LogisticPermissionModel;
import kg.founders.core.settings.security.permission.AccessPermission;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = LogisticPermission.TABLE_NAME)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LogisticPermission extends BaseEntity implements IdBased {

    @SqlTable
    public static final String TABLE_NAME = "LOGISTIC_PERMISSIONS";
    public static final String SEQ_NAME = TABLE_NAME + "_SEQ";

    @Id
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @GeneratedValue(generator = SEQ_NAME)
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    PermissionType name;

    @Column(length = 512, nullable = false)
    String description;

    public LogisticPermission(Long id) {
        this.id = id;
    }

    public LogisticPermissionModel toModel(AccessPermission accessPermission) {
        return new LogisticPermissionModel(
                getId(),
                getName(),
                accessPermission.getAccess()
        );
    }

}
