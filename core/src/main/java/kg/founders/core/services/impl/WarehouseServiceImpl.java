package kg.founders.core.services.impl;

import kg.founders.core.converter.WarehouseConverter;
import kg.founders.core.entity.Warehouse;
import kg.founders.core.model.WarehouseModel;
import kg.founders.core.repo.WarehouseRepo;
import kg.founders.core.services.WarehouseService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        Warehouse warehouse = warehouseConverter.convertFromModel(warehouseModel);
        warehouseRepo.save(warehouse);
        return warehouseConverter.convertFromEntity(warehouse);
    }

    @Override
    public List<WarehouseModel> getAll() {
        return warehouseRepo.findAll().stream().map(warehouseConverter::convertFromEntity).collect(Collectors.toList());

    }
}
