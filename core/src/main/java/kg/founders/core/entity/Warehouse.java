package kg.founders.core.entity;

import kg.founders.core.entity.dictionaries.CityDict;
import kg.founders.core.util.SqlTable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = Warehouse.TABLE_NAME)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Warehouse extends BaseEntity {
    @SqlTable
    public static final String TABLE_NAME = "WAREHOUSE";
    public static final String SEQ_NAME = TABLE_NAME + "_SEQ";

    @Id
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @GeneratedValue(generator = SEQ_NAME)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(name = "IS_LOCAL", nullable = false)
    boolean isLocal;

    @ManyToOne
    @JoinColumn(name = "CITY_ID", nullable = false)
    CityDict city;

    @Column(nullable = false)
    String address;

    @Column(name = "PHONE_NUMBER")
    String phoneNumber;

    @Column(name = "VOLUME_M3", nullable = false, columnDefinition = "NUMERIC(19, 2) DEFAULT 0")
    BigDecimal volumeM3; // maximum volume capacity in cubic meters

    @Column(name = "WEIGHT_KG", nullable = false, columnDefinition = "NUMERIC(19, 2) DEFAULT 0")
    BigDecimal weightKg; // maximum weight capacity in kilograms
}
