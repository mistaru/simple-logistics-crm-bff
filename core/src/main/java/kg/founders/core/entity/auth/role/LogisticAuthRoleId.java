package kg.founders.core.entity.auth.role;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LogisticAuthRoleId implements Serializable {

    @Column(name = "AUTH_ID")
    Long authId;

    @Column(name = "ROLE_ID")
    Long roleId;
}

