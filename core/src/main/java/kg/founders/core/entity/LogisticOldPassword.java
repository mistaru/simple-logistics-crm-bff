package kg.founders.core.entity;

import kg.founders.core.entity.auth.LogisticAuth;
import kg.founders.core.model.audit.IdBased;
import kg.founders.core.util.SqlTable;
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
@Table(name = LogisticOldPassword.TABLE_NAME)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class LogisticOldPassword extends BaseEntity implements IdBased {
    @SqlTable
    public static final String TABLE_NAME = "LOGISTIC_OLD_PASSWORDS";
    public static final String SEQ_NAME = TABLE_NAME + "_SEQ";

    @Id
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @GeneratedValue(generator = SEQ_NAME)
    Long id;

    String password;

    @ManyToOne
    @JoinColumn(nullable = false)
    LogisticAuth logisticAuth;
}