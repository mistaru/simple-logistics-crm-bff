package kg.founders.common.entities.user;

import jakarta.persistence.*;
import kg.founders.common.enums.PermissionKey;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "permissions")
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name = "id_generator", sequenceName = "permission_id_seq", allocationSize = 1)
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    PermissionKey key;

    String description;
}

