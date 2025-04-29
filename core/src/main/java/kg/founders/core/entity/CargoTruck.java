package kg.founders.core.entity;

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
@Table(name = CargoTruck.TABLE_NAME)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class CargoTruck extends BaseEntity {
    @SqlTable
    public static final String TABLE_NAME = "CARGO_TRUCK";
    public static final String SEQ_NAME = TABLE_NAME + "_SEQ";

    @Id
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @GeneratedValue(generator = SEQ_NAME)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cargo_id", nullable = false)
    Cargo cargo;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "truck_id", nullable = false)
    Truck truck;

}
