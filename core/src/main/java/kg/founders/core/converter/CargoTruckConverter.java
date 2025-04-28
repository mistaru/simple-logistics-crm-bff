package kg.founders.core.converter;

import kg.founders.core.entity.Cargo;
import kg.founders.core.entity.CargoTruck;
import kg.founders.core.entity.Truck;
import kg.founders.core.model.CargoModel;
import kg.founders.core.model.CargoTruckModel;
import kg.founders.core.repo.CargoRepo;
import kg.founders.core.repo.TruckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CargoTruckConverter extends ModelConverter<CargoTruckModel, CargoTruck> {
    final CargoRepo cargoRepo;
    final TruckRepository truckRepo;
    final TruckConverter truckConverter;
    final CargoConverter cargoConverter;

    @PostConstruct
    public void init() {
        this.fromEntity = this::convertCargoTruckToCargoTruckModel;
        this.fromModel = this::convertCargoTruckModelToCargoTruck;
    }

    public CargoTruckModel convertCargoTruckToCargoTruckModel(CargoTruck cargoTruck) {
        if (cargoTruck == null) return null;
        CargoTruckModel cargoTruckModel = new CargoTruckModel();
        cargoTruckModel.setTruck(truckConverter.convertTruckToTruckModel(cargoTruck.getTruck()));
        List<CargoModel> cargos = cargoTruck.getCargo().getId() != null ? List.of(cargoConverter.convertFromEntity(cargoTruck.getCargo())) : null;
        cargoTruckModel.setCargos(cargos);
        return cargoTruckModel;
    }

    public CargoTruck convertCargoTruckModelToCargoTruck(CargoTruckModel cargoTruckModel) {
        CargoTruck cargoTruck = new CargoTruck();
        Truck truck = truckRepo.findById(cargoTruckModel.getTruck().getId()).orElseThrow();
        cargoTruck.setTruck(truck);
        List<Cargo> cargos = cargoTruckModel.getCargos().stream().map(cargo -> cargoRepo.findById(cargo.getId()).orElseThrow()).collect(Collectors.toList());
        for (Cargo cargo: cargos) {
            cargoTruck.setCargo(cargo);
        }
        return cargoTruck;
    }

}
