package kg.founders.bff.controller;

import kg.founders.core.enums.permission.PermissionType;
import kg.founders.core.model.PriceModel;
import kg.founders.core.services.PriceService;
import kg.founders.core.settings.security.permission.annotation.HasPermission;
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
@RequestMapping("/api/price")
@AllArgsConstructor(access = PACKAGE)
@HasPermission(value = PermissionType.CLIENT)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PriceControllerRest {
    PriceService service;

    @GetMapping("/all")
    public List<PriceModel> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriceModel> getById(@PathVariable int id) {
        PriceModel model = service.getById(id);
        if (model == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(model);
    }

    @PostMapping
    public PriceModel create(@RequestBody PriceModel model) {
        return service.save(model);
    }

    @PutMapping
    public PriceModel update(@RequestBody PriceModel model) {
        return service.update(model);
    }
}