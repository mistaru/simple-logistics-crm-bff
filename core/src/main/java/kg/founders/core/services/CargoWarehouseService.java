package kg.founders.core.services;

import kg.founders.core.entity.Cargo;
import kg.founders.core.entity.Warehouse;

import java.util.List;

public interface CargoWarehouseService {

    List<Cargo> getCargosByWarehouseId(Long warehouseId);

    List<Warehouse> getWarehousesByCargoId(Long cargoId);

    void assignCargoToWarehouse(Long cargoId, Long warehouseId);

    void unassignCargoFromWarehouse(Long cargoId, Long warehouseId);
}
