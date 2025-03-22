package kg.founders.bff.controller;

import kg.founders.core.enums.permission.PermissionType;
import kg.founders.core.model.TruckModel;
import kg.founders.core.services.TruckService;
import kg.founders.core.settings.security.permission.annotation.HasPermission;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@RestController
@RequestMapping("/api/truck")
@AllArgsConstructor(access = PACKAGE)
@HasPermission(value = PermissionType.TRUCK)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TruckControllerRest {
    TruckService truckService;

    @GetMapping
    public List<TruckModel> getAll() {
        return truckService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TruckModel> getById(@PathVariable int id) {
        TruckModel truckModel = truckService.getById(id);
        if (truckModel == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(truckModel);
    }

    @PostMapping
    public TruckModel create(@RequestBody TruckModel truckModel) {
        return truckService.save(truckModel);
    }

    @PutMapping
    public TruckModel update(@RequestBody TruckModel truckModel) {
        return truckService.update(truckModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        truckService.softDelete(id);
    }

}
