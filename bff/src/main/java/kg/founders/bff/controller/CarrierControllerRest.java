package kg.founders.bff.controller;

import kg.founders.core.enums.permission.PermissionType;
import kg.founders.core.model.CarrierModel;
import kg.founders.core.model.CarrierProfileModel;
import kg.founders.core.services.CarrierService;
import kg.founders.core.settings.security.permission.annotation.HasPermission;
import kg.founders.core.settings.security.permission.annotation.HasPermissions;
import kg.founders.core.settings.security.permission.annotation.ManualPermissionControl;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kg.founders.core.enums.permission.PermissionType.CARRIER;
import static kg.founders.core.enums.permission.PermissionType.TRUCK;
import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@RestController
@RequestMapping("/api/carrier")
@AllArgsConstructor(access = PACKAGE)
@HasPermission(CARRIER)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CarrierControllerRest {

    CarrierService carrierService;

    @GetMapping
    @HasPermission(CARRIER)
    public List<CarrierModel> getAll() {
        return carrierService.getAll();
    }

    @GetMapping("/{id}")
    @HasPermissions({
            @HasPermission(CARRIER),
            @HasPermission(TRUCK)
    })
    public ResponseEntity<CarrierProfileModel> getById(@PathVariable Long id) {
        CarrierProfileModel carrierModel = carrierService.getById(id);
        if (carrierModel == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(carrierModel);
    }

    @PostMapping
    @HasPermission(CARRIER)
    public CarrierModel create(@RequestBody CarrierModel carrierModel) {
        return carrierService.save(carrierModel);
    }

    @PutMapping
    @HasPermission(CARRIER)
    public CarrierModel update(@RequestBody CarrierModel carrierModel) {
        return carrierService.update(carrierModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @HasPermission(CARRIER)
    public void delete(@PathVariable Long id) {
        carrierService.softDelete(id);
    }

    @GetMapping("/ids")
    @ManualPermissionControl
    public List<Long> getCarrierIds() {
        return carrierService.getCarrierIds();
    }
}
