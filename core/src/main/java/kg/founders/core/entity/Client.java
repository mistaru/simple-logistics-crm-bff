package kg.founders.core.entity;

import kg.founders.core.model.ClientModel;
import kg.founders.core.util.SqlTable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = Client.TABLE_NAME)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Client extends BaseEntity {
    @SqlTable
    public static final String TABLE_NAME = "CLIENT";
    public static final String SEQ_NAME = TABLE_NAME + "_SEQ";

    @Id
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @GeneratedValue(generator = SEQ_NAME)
    Long id;

    @Column(name = "FULL_NAME", nullable = false)
    String fullName;

    @Column(name = "CLIENT_CODE", nullable = false)
    String clientCode;

    @Column(name = "PHONE_NUMBER", nullable = false, unique = true)
    String phoneNumber;

    @Column(name = "WHATSAPP_NUMBER")
    String whatsappNumber;

    @Column(name = "EMAIL", unique = true)
    String email;

    @Column(name = "ADDITIONAL_INFO")
    String additionalInfo;

    public ClientModel toModel() {
        return new ClientModel(
                id,
                fullName,
                clientCode,
                phoneNumber,
                whatsappNumber,
                email,
                additionalInfo
        );
    }
}
