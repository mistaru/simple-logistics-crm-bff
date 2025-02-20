package kg.founders.bff.controller.dictionaries;

import kg.founders.core.enums.permission.PermissionType;
import kg.founders.core.model.dictionaries.CityDictModel;
import kg.founders.core.services.dictionaries.CityDictService;
import kg.founders.core.settings.security.permission.annotation.HasPermission;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@RestController
@RequestMapping("/api/city")
@AllArgsConstructor(access = PACKAGE)
@HasPermission(value = PermissionType.PERMISSION)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CityDictControllerRest {
    CityDictService cityDictService;

    @GetMapping("/all")
    public List<CityDictModel> getAll() {
        return cityDictService.getAll();
    }

    @GetMapping
    public Page<CityDictModel> get(Pageable pageable) {
        return cityDictService.get(pageable);
    }

    @PostMapping
    public CityDictModel create(@RequestBody CityDictModel cityDictModel) throws Exception {
        return cityDictService.create(cityDictModel);
    }

    @PutMapping
    public CityDictModel update(@RequestBody CityDictModel cityDictModel) {
        return cityDictService.update(cityDictModel);
    }
}
