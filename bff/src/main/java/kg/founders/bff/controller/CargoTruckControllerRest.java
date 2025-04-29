package kg.founders.bff.controller;

import kg.founders.core.enums.permission.PermissionType;
import kg.founders.core.model.CargoModel;
import kg.founders.core.model.CargoTruckModel;
import kg.founders.core.services.CargoTruckService;
import kg.founders.core.settings.security.permission.annotation.HasPermission;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@RestController
@RequestMapping("/api/cargo-truck")
@AllArgsConstructor(access = PACKAGE)
@HasPermission(value = PermissionType.CARGO_TRUCK)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CargoTruckControllerRest {
    CargoTruckService cargoTruckService;

    @GetMapping
    public List<CargoTruckModel> getAll() {
        return cargoTruckService.getAll();
    }

    @PostMapping
    public CargoTruckModel create(@RequestBody CargoTruckModel cargoTruckModel) {
        return cargoTruckService.create(cargoTruckModel);
    }

    @GetMapping("/{truckId}")
    public CargoTruckModel getCargoTruckByTruckId(@PathVariable long truckId) {
        return cargoTruckService.getCargoTruckByTruckId(truckId);
    }

    @GetMapping("/unassigned-cargos")
    public List<CargoModel> getUnassignedCargos() {
        return cargoTruckService.getUnassignedCargos();
    }

    @PostMapping("/assign")
    public void assignCargoToTruck(@RequestParam Long cargoId, @RequestParam Long truckId){
        cargoTruckService.assignCargoToTruck(cargoId, truckId);
    }

    @PostMapping("/unassign")
    public void unassignCargoFromTruck(@RequestParam Long cargoId, @RequestParam Long truckId){
        cargoTruckService.unassignCargoFromTruck(cargoId, truckId);
    }

}
