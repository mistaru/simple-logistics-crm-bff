package kg.founders.core.services.impl;

import kg.founders.core.converter.WarehouseConverter;
import kg.founders.core.entity.Warehouse;
import kg.founders.core.enums.CargoStatus;
import kg.founders.core.model.WarehouseModel;
import kg.founders.core.repo.WarehouseRepo;
import kg.founders.core.services.WarehouseService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class WarehouseServiceImpl implements WarehouseService {
    WarehouseRepo warehouseRepo;
    WarehouseConverter warehouseConverter;

    @Override
    public Page<WarehouseModel> get(Pageable pageable) {
        return warehouseRepo.findAll(pageable).map(warehouseConverter::convertFromEntity);
    }

    @Override
    public WarehouseModel create(WarehouseModel warehouseModel) throws Exception {
        Warehouse warehouse = warehouseConverter.convertFromModel(warehouseModel);
        warehouseRepo.save(warehouse);
        return warehouseConverter.convertFromEntity(warehouse);
    }

    @Override
    public WarehouseModel update(WarehouseModel warehouseModel) {
        Warehouse existingWarehouse = warehouseRepo.findById(warehouseModel.getId())
                .orElseThrow(() -> new EntityNotFoundException("Warehouse not found with id: " + warehouseModel.getId()));

        existingWarehouse.setName(warehouseModel.getName());
        existingWarehouse.setLocal(warehouseModel.isLocal());
        existingWarehouse.setAddress(warehouseModel.getAddress());
        existingWarehouse.setPhoneNumber(warehouseModel.getPhoneNumber());
        existingWarehouse.setVolumeM3(warehouseModel.getVolumeM3());
        existingWarehouse.setWeightKg(warehouseModel.getWeightKg());

        Warehouse updatedWarehouse = warehouseRepo.save(existingWarehouse);
        return warehouseConverter.convertFromEntity(updatedWarehouse);
    }

    @Override
    public List<WarehouseModel> getAll() {
        return warehouseRepo.findAllWithOccupiedCapacity(CargoStatus.IN_WAREHOUSE_STATUSES)
                .stream().map(warehouseConverter::convertFromEntity).toList();
    }
}
