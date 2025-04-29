package kg.founders.core.entity;

import kg.founders.core.enums.PaymentStatus;
import kg.founders.core.util.SqlTable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = Payment.TABLE_NAME)
public class Payment extends BaseEntity {
    @SqlTable
    public static final String TABLE_NAME = "PAYMENT";
    public static final String SEQ_NAME = TABLE_NAME + "_SEQ";

    @Id
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @GeneratedValue(generator = SEQ_NAME)
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    PaymentStatus status;

    @ManyToOne
    Cargo cargo;

    Timestamp planned;

    Timestamp actual;

    String comment;

    Long managerId;
}
