package kg.founders.common.entities.cargo;

import jakarta.persistence.*;
import kg.founders.common.entities.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "cargo")
@SequenceGenerator(name = "id_generator", sequenceName = "cargo_id_seq", allocationSize = 1)
public class Cargo extends BaseEntity {
    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    Double weight;

    @Column(nullable = false)
    Double volume;

    @Column(nullable = false)
    Integer unitCount;

    @Column(nullable = false)
    LocalDateTime arrivalDate;

    LocalDateTime departureDate;

    @ElementCollection
    @CollectionTable(name = "delivery_routes", joinColumns = @JoinColumn(name = "cargo_item_id"))
    @Column(name = "route")
    List<String> deliveryRoutes;

    @Column(name = "client_id", nullable = false)
    Long clientId;

}
