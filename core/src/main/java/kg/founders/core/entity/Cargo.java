package kg.founders.core.entity;

import kg.founders.core.enums.CargoStatus;
import kg.founders.core.util.SqlTable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = Cargo.TABLE_NAME)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Cargo extends BaseEntity {
    @SqlTable
    public static final String TABLE_NAME = "CARGO";
    public static final String SEQ_NAME = TABLE_NAME + "_SEQ";

    @Id
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @GeneratedValue(generator = SEQ_NAME)
    Long id;
    double weight;
    double volume;
    int quantity;
    Timestamp warehouseArrivalDate;
    Timestamp shipmentDate;

    @ManyToOne(fetch = FetchType.EAGER)
    Client client;

    @Enumerated(EnumType.STRING)
    CargoStatus status;
    String description;
    @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL, orphanRemoval = true)
    List<CargoTruck> cargoTrucks;
    Long managerId;

    public Cargo(Long id) {
        this.id = id;
    }

    public Cargo() {
    }
}
