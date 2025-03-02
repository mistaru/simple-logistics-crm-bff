package kg.founders.core.services;

import kg.founders.core.entity.Cargo;
import kg.founders.core.entity.Truck;

import java.util.List;

public interface CargoTruckService {

    List<Cargo> getCargosByTruckId(Long truckId);

    List<Truck> getTrucksByCargoId(Long cargoId);

    void assignCargoToTruck(Long cargoId, Long truckId);

    void unassignCargoFromTruck(Long cargoId, Long truckId);
}
