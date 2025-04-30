package kg.founders.bff.controller;

import kg.founders.core.enums.permission.PermissionType;
import kg.founders.core.exceptions.ForbiddenException;
import kg.founders.core.model.CargoModel;
import kg.founders.core.model.ReassignCargosRequest;
import kg.founders.core.services.CargoService;
import kg.founders.core.settings.security.permission.annotation.HasPermission;
import kg.founders.core.settings.security.permission.annotation.ManualPermissionControl;
import kg.founders.core.util.PermissionHelper;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@RestController
@RequestMapping("/api/cargo")
@AllArgsConstructor(access = PACKAGE)
@HasPermission(value = PermissionType.CARGO)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CargoControllerRest {
    private final CargoService cargoService;
    private final PermissionHelper permissionHelper;

    // Получение списка грузов
    @GetMapping
    @ManualPermissionControl
    public List<CargoModel> findAll() {
        if (permissionHelper.isAdmin()) {
            return cargoService.findALl();
        } else {
            return cargoService.findALlByManagerId(permissionHelper.currentUserId());
        }
    }

    // Добавление нового груза
    @PostMapping
    @ManualPermissionControl
    public CargoModel createCargo(@RequestBody CargoModel cargoModel) {
        cargoModel.setManagerId(permissionHelper.currentUserId());
        return cargoService.saveCargo(cargoModel);
    }

    // Обновление существующего груза
    @PutMapping
    @ManualPermissionControl
    public CargoModel updateCargo(@RequestBody CargoModel cargoModel) {
        return cargoService.saveCargo(cargoModel);
    }

    @PostMapping("/reassign")
    @ManualPermissionControl
    public ResponseEntity<Void> reassignCargo(@RequestBody Long managerId, @RequestBody Long cargoId) {
        if (!permissionHelper.isAdmin()) throw new ForbiddenException();
        cargoService.reassign(managerId, cargoId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reassign-all")
    @ManualPermissionControl
    public ResponseEntity<Void> reassignAll(@RequestBody ReassignCargosRequest reassignCargosRequest) {
        if (!permissionHelper.isAdmin()) throw new ForbiddenException();
        cargoService.reassignAll(reassignCargosRequest);
        return ResponseEntity.ok().build();
    }

    // Удаление груза
    @DeleteMapping("/{id}")
    @ManualPermissionControl
    public ResponseEntity<Void> deleteCargo(@PathVariable Long id) {
        cargoService.deleteCargo(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/is-linked")
    public boolean isLinked(@RequestParam Long userId) {
        return cargoService.existsByManagerId(userId);
    }
}


