package kg.founders.core.services.impl;

import kg.founders.core.converter.CargoConverter;
import kg.founders.core.converter.CargoTruckConverter;
import kg.founders.core.converter.TruckConverter;
import kg.founders.core.entity.Cargo;
import kg.founders.core.entity.CargoTruck;
import kg.founders.core.entity.Truck;
import kg.founders.core.model.CargoModel;
import kg.founders.core.model.CargoTruckModel;
import kg.founders.core.model.TruckModel;
import kg.founders.core.repo.CargoRepo;
import kg.founders.core.repo.CargoTruckRepo;
import kg.founders.core.repo.TruckRepository;
import kg.founders.core.services.CargoTruckService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
    CargoTruckConverter cargoTruckConverter;
    CargoConverter cargoConverter;
    TruckConverter truckConverter;

    @Override
    @Transactional
    public List<CargoTruckModel> getAll() {
        List<Truck> trucks = truckRepo.findAll().stream()
                .filter(truck -> truck.getRdt() == null)
                .collect(Collectors.toList());

        List<CargoTruckModel> cargoTrucks = new ArrayList<>();
        for (Truck truck : trucks) {
            cargoTrucks.add(getCargoTruckByTruckId(truck.getId()));
        }

        return cargoTrucks;
    }

    @Override
    public CargoTruckModel create(CargoTruckModel cargoTruckModel) {
        Truck truck = truckRepo.findById(cargoTruckModel.getTruck().getId()).orElseThrow();
        List<Cargo> cargoes = cargoTruckModel.getCargos().stream().map(cargo -> cargoRepo.findById(cargo.getId()).orElseThrow()).collect(Collectors.toList());
        for (Cargo cargo : cargoes) {
            CargoTruck cargoTruck = new CargoTruck();
            cargoTruck.setCargo(cargo);
            cargoTruck.setTruck(truck);
            cargoTruckRepo.save(cargoTruck);
        }
        return cargoTruckModel;
    }

    @Override
    @Transactional
    public CargoTruckModel getCargoTruckByTruckId(Long truckId) {
        TruckModel truckModel = truckRepo.findById(truckId).stream()
                .map(truckConverter::convertFromEntity)
                .findFirst()
                .orElseThrow();
        List<CargoModel> cargoModels =  cargoTruckRepo.findAllByTruckIdAndRdtIsNull(truckId).stream()
                .map(CargoTruck::getCargo)
                .map(cargoConverter::convertFromEntity)
                .collect(Collectors.toList());
        CargoTruckModel cargoTruckModel = new CargoTruckModel();
        cargoTruckModel.setTruck(truckModel);
        cargoTruckModel.setCargos(cargoModels);
        return cargoTruckModel;
    }

    @Override
    public List<Truck> getTrucksByCargoId(Long cargoId) {
        return cargoTruckRepo.findAllByCargoIdAndRdtIsNull(cargoId).stream()
                .map(CargoTruck::getTruck)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void assignCargoToTruck(Long cargoId, Long truckId) {
        Cargo cargo = cargoRepo.findById(cargoId).orElseThrow();
        Truck truck = truckRepo.findById(truckId).orElseThrow();

        double cargoTotalVolumeM3 = cargo.getVolume() * cargo.getQuantity();
        truck.setVolumeOccupiedM3(truck.getVolumeOccupiedM3() + cargoTotalVolumeM3);
        truck.setVolumeAvailableM3(truck.getVolumeAvailableM3() - cargoTotalVolumeM3);
        truckRepo.save(truck);

        CargoTruck cargoTruck = new CargoTruck();
        cargoTruck.setCargo(cargo);
        cargoTruck.setTruck(truck);
        cargoTruckRepo.save(cargoTruck);
    }

    @Override
    @Transactional
    public void unassignCargoFromTruck(Long cargoId, Long truckId) {
        List<CargoTruck> cargoTrucks = cargoTruckRepo.findAllByCargoIdAndRdtIsNull(cargoId)
                .stream()
                .filter(ct -> ct.getTruck().getId().equals(truckId))
                .collect(Collectors.toList());

        cargoTrucks.forEach(cargoTruck -> {
            Truck truck = cargoTruck.getTruck();
            Cargo cargo = cargoTruck.getCargo();

            double cargoTotalVolumeM3 = cargo.getVolume() * cargo.getQuantity();
            truck.setVolumeOccupiedM3(truck.getVolumeOccupiedM3() - cargoTotalVolumeM3);
            truck.setVolumeAvailableM3(truck.getVolumeAvailableM3() + cargoTotalVolumeM3);
            truckRepo.save(truck);

            cargoTruck.setRdt(new Timestamp(System.currentTimeMillis()));
            cargoTruckRepo.save(cargoTruck);
        });
    }

    @Override
    public List<CargoModel> getUnassignedCargos() {
        List<Cargo> allCargos = cargoRepo.findAll();
        Set<Long> assignedCargoIds = cargoTruckRepo.findAll().stream()
                .filter(cargoTruck -> cargoTruck.getRdt() == null)
                .map(cargoTruck -> cargoTruck.getCargo().getId())
                .collect(Collectors.toSet());

        return allCargos.stream()
                .filter(cargo -> !assignedCargoIds.contains(cargo.getId()))
                .map(cargoConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

}
