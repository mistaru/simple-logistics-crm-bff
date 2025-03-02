package kg.founders.core.services.impl;

import kg.founders.core.entity.Cargo;
import kg.founders.core.entity.CargoTruck;
import kg.founders.core.entity.Truck;
import kg.founders.core.repo.CargoRepo;
import kg.founders.core.repo.CargoTruckRepo;
import kg.founders.core.repo.TruckRepository;
import kg.founders.core.services.CargoTruckService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CargoTruckServiceImpl implements CargoTruckService {
    CargoTruckRepo cargoTruckRepo;
    CargoRepo cargoRepo;
    TruckRepository truckRepo;

    // Получить список грузов по ID грузовика
    public List<Cargo> getCargosByTruckId(Long truckId) {
        List<Long> cargoIds = cargoTruckRepo.findByTruckId(truckId)
                .stream()
                .map(CargoTruck::getCargoId)
                .collect(Collectors.toList());

        return cargoRepo.findAllById(cargoIds);
    }

    // Получить список грузовиков по ID груза
    public List<Truck> getTrucksByCargoId(Long cargoId) {
        List<Long> truckIds = cargoTruckRepo.findByCargoId(cargoId)
                .stream()
                .map(CargoTruck::getTruckId)
                .collect(Collectors.toList());

        return truckRepo.findAllById(truckIds);
    }

    public void assignCargoToTruck(Long cargoId, Long truckId) {
        CargoTruck cargoTruck = new CargoTruck();
        cargoTruck.setCargoId(cargoId);
        cargoTruck.setTruckId(truckId);
        cargoTruckRepo.save(cargoTruck);
    }

    public void unassignCargoFromTruck(Long cargoId, Long truckId) {
        List<CargoTruck> cargoTrucks = cargoTruckRepo.findByCargoId(cargoId)
                .stream()
                .filter(ct -> ct.getTruckId().equals(truckId))
                .collect(Collectors.toList());

        cargoTrucks.forEach(cargoTruck -> {
            cargoTruck.setRdt(new Timestamp(System.currentTimeMillis()));
            cargoTruckRepo.save(cargoTruck);
        });
    }
}
