package kg.founders.bff.controller;

import kg.founders.core.enums.permission.PermissionType;
import kg.founders.core.model.CargoModel;
import kg.founders.core.services.CargoService;
import kg.founders.core.settings.security.permission.annotation.HasPermission;
import kg.founders.core.settings.security.permission.annotation.ManualPermissionControl;
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
@HasPermission(value = PermissionType.CLIENT)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CargoControllerRest {
    private final CargoService cargoService;

    // Получение списка грузов
    @GetMapping
    @ManualPermissionControl
    public List<CargoModel> findAll() {
        return cargoService.findALl();
    }

    // Добавление нового груза
    @PostMapping
    @ManualPermissionControl
    public CargoModel createCargo(@RequestBody CargoModel cargoModel) {
        return cargoService.saveCargo(cargoModel);
    }

    // Обновление существующего груза
    @PutMapping
    @ManualPermissionControl
    public CargoModel updateCargo(@RequestBody CargoModel cargoModel) {
        return cargoService.saveCargo(cargoModel);
    }

    // Удаление груза
    @DeleteMapping("/{id}")
    @ManualPermissionControl
    public ResponseEntity<Void> deleteCargo(@PathVariable Long id) {
        cargoService.deleteCargo(id);
        return ResponseEntity.ok().build();
    }
}


