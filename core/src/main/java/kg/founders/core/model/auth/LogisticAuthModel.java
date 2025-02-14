package kg.founders.core.model.auth;

import kg.founders.core.model.audit.IdBased;
import kg.founders.core.model.auth.role.LogisticRoleModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LogisticAuthModel implements IdBased {
    Long id;
    String username;
    String password;
    Timestamp blocked;
    Set<LogisticRoleModel> roles;
}
