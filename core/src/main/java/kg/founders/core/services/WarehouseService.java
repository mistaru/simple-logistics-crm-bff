package kg.founders.core.services;

import kg.founders.core.model.WarehouseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WarehouseService {

    Page<WarehouseModel> get(Pageable pageable);

    WarehouseModel create(WarehouseModel warehouseModel) throws Exception ;

    WarehouseModel update(WarehouseModel warehouseModel);
}
