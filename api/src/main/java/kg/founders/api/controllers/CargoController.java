package kg.founders.api.controllers;

import kg.founders.api.services.CargoService;
import kg.founders.common.annotations.ResponseMessageController;
import kg.founders.common.models.cargo.CargoModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@ResponseMessageController("/api/cargo")
@RequiredArgsConstructor
public class CargoController {
    private final CargoService cargoService;

    @GetMapping
    public Page<CargoModel> get(Pageable pageable) {
        return cargoService.get(pageable);
    }

    @PostMapping
    public CargoModel create(@RequestBody CargoModel userModel) throws Exception {
        return cargoService.create(userModel);
    }

    @PutMapping
    public CargoModel update(@RequestBody CargoModel userModel) {
        return cargoService.update(userModel);
    }
}
