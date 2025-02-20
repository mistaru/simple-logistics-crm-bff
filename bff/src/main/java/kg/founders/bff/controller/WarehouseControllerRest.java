package kg.founders.bff.controller;

import kg.founders.core.enums.permission.PermissionType;
import kg.founders.core.model.WarehouseModel;
import kg.founders.core.services.WarehouseService;
import kg.founders.core.settings.security.permission.annotation.HasPermission;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@RestController
@RequestMapping("/api/warehouse")
@AllArgsConstructor(access = PACKAGE)
@HasPermission(value = PermissionType.PERMISSION)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class WarehouseControllerRest {
    WarehouseService warehouseService;

    @GetMapping
    public Page<WarehouseModel> get(Pageable pageable) {
        return warehouseService.get(pageable);
    }

    @PostMapping
    public WarehouseModel create(@RequestBody WarehouseModel warehouseModel) throws Exception {
        return warehouseService.create(warehouseModel);
    }

    @PutMapping
    public WarehouseModel update(@RequestBody WarehouseModel warehouseModel) {
        return warehouseService.update(warehouseModel);
    }
}
