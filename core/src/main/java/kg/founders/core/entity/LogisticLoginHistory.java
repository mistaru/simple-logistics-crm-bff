package kg.founders.core.entity;

import kg.founders.core.model.audit.IdBased;
import kg.founders.core.util.SqlTable;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = LogisticLoginHistory.TABLE_NAME , indexes = {@Index(columnList = "login")})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LogisticLoginHistory implements IdBased, Serializable {

    @SqlTable
    public static final String TABLE_NAME = "LOGISTIC_LOGIN_HISTORY";
    public static final String SEQ_NAME = TABLE_NAME + "_SEQ";

    @Id
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @GeneratedValue(generator = SEQ_NAME)
    Long id;

    Timestamp cdt;

    String login;

    String ip;
}