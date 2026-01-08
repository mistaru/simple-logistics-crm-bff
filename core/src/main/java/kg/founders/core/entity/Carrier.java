package kg.founders.core.entity;

import kg.founders.core.util.SqlTable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = Carrier.TABLE_NAME)
@EntityListeners(AuditingEntityListener.class)
public class Carrier extends BaseEntity {

    @SqlTable
    public static final String TABLE_NAME = "CARRIER";
    public static final String SEQ_NAME = TABLE_NAME + "_SEQ";

    @Id
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @GeneratedValue(generator = SEQ_NAME)
    Long id;

    @Column(nullable = false)
    String name;

    @Column
    String email;

    @Column(nullable = false)
    String phoneNumber;

    @Column(nullable = false)
    BigDecimal balance;

    @OneToMany(mappedBy = "carrier")
    List<Truck> trucks;

}
