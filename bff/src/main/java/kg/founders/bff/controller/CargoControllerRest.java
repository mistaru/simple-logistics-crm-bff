package kg.founders.bff.controller;

import kg.founders.core.model.CargoModel;
import kg.founders.core.services.CargoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/cargo")
public class CargoControllerRest {
    private final CargoService cargoService;

    // Получение списка грузов
    @GetMapping
    public List<CargoModel> findAll() {
        return cargoService.findALl();
    }

    // Добавление нового груза
    @PostMapping
    public CargoModel createCargo(@RequestBody CargoModel cargoModel) {
        return cargoService.saveCargo(cargoModel);
    }

    // Обновление существующего груза
    @PutMapping
    public CargoModel updateCargo(@RequestBody CargoModel cargoModel) {
        return cargoService.saveCargo(cargoModel);
    }

    // Удаление груза
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCargo(@PathVariable Long id) {
        cargoService.deleteCargo(id);
        return ResponseEntity.ok().build();
    }
}


