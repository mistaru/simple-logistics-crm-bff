package kg.founders.core.entity.auth.role;

import kg.founders.core.entity.BaseEntity;
import kg.founders.core.entity.auth.permission.LogisticRolePermission;
import kg.founders.core.model.audit.IdBased;
import kg.founders.core.util.SqlTable;
import kg.founders.core.model.auth.role.LogisticRoleModel;
import kg.founders.core.model.auth.role.permission.LogisticPermissionModel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = LogisticRole.TABLE_NAME)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class LogisticRole extends BaseEntity implements IdBased {

    @SqlTable
    public static final String TABLE_NAME = "LOGISTIC_ROLES";
    public static final String SEQ_NAME = TABLE_NAME + "_SEQ";

    @Id
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @GeneratedValue(generator = SEQ_NAME)
    Long id;

    @Column(nullable = false, unique = true)
    String name;

    @Column(length = 1000)
    String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "logisticRole")
    List<LogisticRolePermission> logisticRolePermissions;

    public LogisticRole(Long id) {
        this.id = id;
    }

    public LogisticRoleModel toModel() {
        List<LogisticPermissionModel> logisticPermissionModels = Collections.emptyList();

        var permissions = getLogisticRolePermissions();

        if (permissions != null) {
            logisticPermissionModels = permissions.stream()
                    .map(p -> p.getLogisticPermission().toModel(p))
                    .collect(Collectors.toList());
        }

        return LogisticRoleModel.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .permissions(logisticPermissionModels)
                .build();
    }
}
