package kg.founders.core.entity.auth.role;

import kg.founders.core.entity.auth.LogisticAuth;
import kg.founders.core.util.SqlTable;
import kg.founders.core.util.GsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = LogisticAuthRole.TABLE_NAME)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LogisticAuthRole implements Serializable {

    @SqlTable
    public static final String TABLE_NAME = "LOGISTIC_AUTH_ROLES";
    public static final String SEQ_NAME = TABLE_NAME + "_SEQ";

    @EmbeddedId
    LogisticAuthRoleId id;

    @ManyToOne
    @GsonIgnore
    @MapsId("authId")
    LogisticAuth logisticAuth;

    @ManyToOne
    @GsonIgnore
    @MapsId("roleId")
    LogisticRole logisticRole;

    @Column(name = "ACTIVE")
    Boolean active;

    public static LogisticAuthRole createForUpdate(LogisticAuth logisticAuth, LogisticRole logisticRole, Boolean active) {
        var ar = new LogisticAuthRole();
        ar.id = new LogisticAuthRoleId(logisticAuth.getId(), logisticRole.getId());
        ar.logisticAuth = logisticAuth;
        ar.logisticRole = logisticRole;
        ar.active = active;
        return ar;
    }

    public static LogisticAuthRole createForInsert(LogisticAuth logisticAuth, LogisticRole logisticRole, Boolean active) {
        var ar = new LogisticAuthRole();
        ar.id = new LogisticAuthRoleId();
        ar.logisticAuth = logisticAuth;
        ar.logisticRole = logisticRole;
        ar.active = active;
        return ar;
    }
}