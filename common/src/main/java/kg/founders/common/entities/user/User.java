package kg.founders.common.entities.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kg.founders.common.entities.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")},
        indexes = {@Index(columnList = "username")})
@SequenceGenerator(name = "id_generator", sequenceName = "user_id_seq", allocationSize = 1)
public class User extends BaseEntity {
    @Column(name = "fullname", columnDefinition = "VARCHAR(255)")
    String fullname;

    @NotBlank
    @Size(max = 20)
    @Column(name = "username", nullable = false)
    String username;

    @NotBlank
    @Size(max = 50)
    @Email
    String email;

    @NotBlank
    @Size(max = 120)
    String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    Role role;

    @Column(name = "phone_number")
    String phoneNumber;

    public User() {}

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

