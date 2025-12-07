package kg.founders.core.entity;

import kg.founders.core.util.SqlTable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = Truck.TABLE_NAME)
public class Truck extends BaseEntity {

    @SqlTable
    public static final String TABLE_NAME = "TRUCK";
    public static final String SEQ_NAME = TABLE_NAME + "_SEQ";

    @Id
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @GeneratedValue(generator = SEQ_NAME)
    Long id;

    @Column(nullable = false, name = "registration_country")
    String registrationCountry;

    @Column(nullable = false, name = "volume_total_m3")
    double volumeTotalM3;

    @Column(nullable = false, name = "volume_occupied_m3")
    double volumeOccupiedM3;

    @Column(nullable = false, name = "volume_available_m3")
    double volumeAvailableM3;

    @Column(nullable = false, name = "departure_warehouse")
    String departureWarehouse;

    @Column(nullable = false, name = "arrival_warehouse")
    String arrivalWarehouse;

    @Column(nullable = false, name = "driver_fullname")
    String driverFullname;

    @Column(nullable = false, name = "driver_phone")
    String driverPhone;

    @Column(nullable = false, name = "departure_date_planned")
    Timestamp departureDatePlanned;

    @Column(nullable = false, name = "departure_date_actual")
    Timestamp departureDateActual;

    @Column(nullable = false, name = "arrival_date_planned")
    Timestamp arrivalDatePlanned;

    @Column(nullable = false, name = "arrival_date_actual")
    Timestamp arrivalDateActual;

    @Column
    String carrier;

    @Column(nullable = false, name = "service_fee")
    BigDecimal serviceFee; // TODO это поле нужно?

    @Column(name = "customs_fee")
    BigDecimal customsFee;

    @Column
    BigDecimal expenses;

    @Column(name = "additional_expenses")
    BigDecimal additionalExpenses;

    @Column(name = "total_amount")
    BigDecimal totalAmount;

    @Column(name = "additional_information")
    String additionalInformation;

    // Перевозчик, Стоимость таможни, Дополнительные расходы, Расходы, Сумма

    @OneToMany(mappedBy = "truck")
    List<CargoTruck> cargoTrucks;

    /**
     * This method is automatically called by JPA before a new Truck entity is first saved (persisted)
     * and before an existing one is updated.
     */
    @PrePersist
    @PreUpdate
    public void calculateTotalAmount() {
        BigDecimal calculatedTotal = BigDecimal.ZERO;

        calculatedTotal = calculatedTotal.add(Optional.ofNullable(this.serviceFee).orElse(BigDecimal.ZERO)); // TODO это поле нужно?
        calculatedTotal = calculatedTotal.add(Optional.ofNullable(this.customsFee).orElse(BigDecimal.ZERO));
        calculatedTotal = calculatedTotal.add(Optional.ofNullable(this.expenses).orElse(BigDecimal.ZERO));
        calculatedTotal = calculatedTotal.add(Optional.ofNullable(this.additionalExpenses).orElse(BigDecimal.ZERO));

        this.totalAmount = calculatedTotal;
    }

}
