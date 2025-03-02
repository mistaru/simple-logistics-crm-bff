package kg.founders.core.services.impl;

import kg.founders.core.entity.*;
import kg.founders.core.repo.CargoRepo;
import kg.founders.core.repo.CargoWarehouseRepo;
import kg.founders.core.repo.WarehouseRepo;
import kg.founders.core.services.CargoWarehouseService;
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
public class CargoWarehouseServiceImpl implements CargoWarehouseService {

    WarehouseRepo warehouseRepo;
    CargoRepo cargoRepo;
    CargoWarehouseRepo cargoWarehouseRepo;

    @Override
    public List<Cargo> getCargosByWarehouseId(Long warehouseId) {
        List<Long> cargoIds = cargoWarehouseRepo.findAllByWarehouseIdAndRdtIsNull(warehouseId)
                .stream()
                .map(CargoWarehouse::getCargoId)
                .collect(Collectors.toList());

        return cargoRepo.findAllById(cargoIds);
    }

    @Override
    public List<Warehouse> getWarehousesByCargoId(Long cargoId) {
        List<Long> warehouses = cargoWarehouseRepo.findAllByCargoIdAndRdtIsNull(cargoId)
                .stream()
                .map(CargoWarehouse::getWarehouseId)
                .collect(Collectors.toList());

        return warehouseRepo.findAllById(warehouses);
    }

    @Override
    public void assignCargoToWarehouse(Long cargoId, Long warehouseId) {
        CargoWarehouse cargoWarehouse = new CargoWarehouse();
        cargoWarehouse.setCargoId(cargoId);
        cargoWarehouse.setWarehouseId(warehouseId);
        cargoWarehouseRepo.save(cargoWarehouse);
    }

    @Override
    public void unassignCargoFromWarehouse(Long cargoId, Long warehouseId) {
        List<CargoWarehouse> cargoWarehouses = cargoWarehouseRepo.findAllByCargoIdAndRdtIsNull(cargoId)
                .stream()
                .filter(ct -> ct.getWarehouseId().equals(warehouseId))
                .collect(Collectors.toList());

        cargoWarehouses.forEach(cargoWarehouse -> {
            cargoWarehouse.setRdt(new Timestamp(System.currentTimeMillis()));
            cargoWarehouseRepo.save(cargoWarehouse);
        });

    }

}
