package kg.founders.core.entity;

import kg.founders.core.util.SqlTable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

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

    @Column(nullable = false, name = "volume_m3")
    double volumeM3;

    @Column(nullable = false, name = "departure_warehouse")
    String departureWarehouse;

    @Column(nullable = false, name = "delivery_warehouse")
    String deliveryWarehouse;

    @Column(nullable = false, name = "driver_phone")
    String driverPhone;

    @Column(name = "additional_information")
    String additionalInformation;

}
