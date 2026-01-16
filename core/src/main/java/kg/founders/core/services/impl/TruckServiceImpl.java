package kg.founders.core.services.impl;

import kg.founders.core.converter.TruckConverter;
import kg.founders.core.entity.Carrier;
import kg.founders.core.entity.Truck;
import kg.founders.core.model.CargoModel;
import kg.founders.core.model.CargoTruckModel;
import kg.founders.core.model.TruckModel;
import kg.founders.core.repo.TruckRepository;
import kg.founders.core.services.CargoService;
import kg.founders.core.services.CargoTruckService;
import kg.founders.core.services.CarrierService;
import kg.founders.core.services.TruckService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    CarrierService carrierService;
    private final CargoService cargoService;

    @Override
    @Transactional(readOnly = true)
    public List<TruckModel> getAll() {
        return truckRepository.findAllWithCarrier().stream()
                .map(truckConverter::convertTruckToTruckModel).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TruckModel getById(int id) {
        return truckConverter.convertTruckToTruckModel(truckRepository.findWithCarrierById((long) id).orElse(null));
    }

    @Override
    @Transactional
    public TruckModel save(TruckModel truckModel) {
        Truck truck = truckConverter.convertTruckModelToTruck(truckModel);

        truckRepository.save(truck);

        Long carrierId = truckModel.getCarrier().getId();
        carrierService.updateCarrierBalance(carrierId, truckRepository.calculateServiceFeeForCarrier(carrierId)); // обновляем баланс перевозчика

        return truckConverter.convertTruckToTruckModel(truck);
    }

    @Override
    @Transactional
    public TruckModel update(TruckModel truckModel) {
        Truck existingTruck = truckRepository.findById(truckModel.getId())
                .orElseThrow(() -> new RuntimeException("Truck not found"));

        truckConverter.updateTruckFromModel(truckModel, existingTruck);

        Truck savedTruck = truckRepository.save(existingTruck);

        Long carrierId = truckModel.getCarrier().getId();
        carrierService.updateCarrierBalance(carrierId, truckRepository.calculateServiceFeeForCarrier(carrierId)); // обновляем баланс перевозчика

        CargoTruckModel cargoTruckModel = cargoTruckService.getCargoTruckByTruckId(existingTruck.getId()); // находим cargo
        List<Long> cargoIds = new ArrayList<>();
        if (cargoTruckModel != null)
            cargoIds = cargoTruckModel.getCargos().stream()
                    .map(CargoModel::getId)
                    .collect(Collectors.toList());

        cargoService.updateStatusForCargos(cargoIds, savedTruck.getStatus());

        return truckConverter.convertTruckToTruckModel(savedTruck);
    }

    @Override
    @Transactional
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

            Long carrierId = truckToDelete.getCarrier().getId();
            carrierService.updateCarrierBalance(carrierId, truckRepository.calculateServiceFeeForCarrier(carrierId)); // обновляем баланс перевозчика
        }
    }

    @Override
    public List<Long> getTruckIds() {
        return truckRepository.findIdByRdtIsNull();
    }

}
