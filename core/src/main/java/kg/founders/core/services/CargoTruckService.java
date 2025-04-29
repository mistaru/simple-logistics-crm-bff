package kg.founders.core.services;

import kg.founders.core.entity.Cargo;
import kg.founders.core.entity.CargoTruck;
import kg.founders.core.entity.Truck;
import kg.founders.core.model.CargoModel;
import kg.founders.core.model.CargoTruckModel;

import java.util.List;
import java.util.Optional;

public interface CargoTruckService {

    List<CargoTruckModel> getAll();

    CargoTruckModel create(CargoTruckModel cargoTruckModel);

    CargoTruckModel getCargoTruckByTruckId(Long truckId);

    List<Truck> getTrucksByCargoId(Long cargoId);

    void assignCargoToTruck(Long cargoId, Long truckId);

    void unassignCargoFromTruck(Long cargoId, Long truckId);

    List<CargoModel> getUnassignedCargos();

}
