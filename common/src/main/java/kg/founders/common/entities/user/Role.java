package kg.founders.common.entities.user;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name = "id_generator", sequenceName = "role_id_seq", allocationSize = 1)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    Long id;

    String name;

    String description;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    Set<User> users;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    Set<RolePermission> permissions;
}
