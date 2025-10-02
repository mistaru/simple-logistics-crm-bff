package kg.founders.core.services.impl;

import kg.founders.core.converter.TruckConverter;
import kg.founders.core.entity.Truck;
import kg.founders.core.model.TruckModel;
import kg.founders.core.repo.TruckRepository;
import kg.founders.core.services.CargoTruckService;
import kg.founders.core.services.TruckService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TruckServiceImpl implements TruckService {

    TruckRepository truckRepository;
    TruckConverter truckConverter;
    CargoTruckService cargoTruckService;

    @Override
    public List<TruckModel> getAll() {
        return truckRepository.findAll().stream()
                .filter(truck -> truck.getRdt() == null)
                .map(truckConverter::convertTruckToTruckModel).collect(Collectors.toList());
    }

    @Override
    public TruckModel getById(int id) {
        return truckConverter.convertTruckToTruckModel(truckRepository.findById((long) id).orElse(null));
    }

    @Override
    public TruckModel save(TruckModel truckModel) {
        Truck truck = truckConverter.convertTruckModelToTruck(truckModel);
        truckRepository.save(truck);
        return truckConverter.convertTruckToTruckModel(truck);
    }

    @Override
    public TruckModel update(TruckModel truckModel) {
//        Optional<Truck> oldTruck = truckRepository.findById(truckModel.getId());
//        Truck newTruck = truckConverter.convertTruckModelToTruck(truckModel);
//        newTruck.setId(oldTruck.get().getId());
//        truckRepository.save(newTruck);
//
//        return truckConverter.convertTruckToTruckModel(newTruck);
        Truck existingTruck = truckRepository.findById(truckModel.getId())
                .orElseThrow(() -> new RuntimeException("Truck not found"));

        truckConverter.updateTruckFromModel(truckModel, existingTruck);

        Truck savedTruck = truckRepository.save(existingTruck);
        return truckConverter.convertTruckToTruckModel(savedTruck);

    }

    @Override
    public void softDelete(int id) {
        Optional<Truck> truck = truckRepository.findWithCargoTrucksById((long) id);
        if (truck.isPresent()) {
            Truck truckToDelete = truck.get();
            truckToDelete.getCargoTrucks()
                .forEach(
                        cargoTruck -> {
                            cargoTruckService.unassignCargoFromTruck(cargoTruck.getCargo().getId(), truckToDelete.getId());
                        }
                );
            truckToDelete.markDeleted();
            truckRepository.save(truck.get());
        }
    }

}

