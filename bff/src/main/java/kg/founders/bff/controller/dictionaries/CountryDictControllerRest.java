package kg.founders.bff.controller.dictionaries;

import kg.founders.core.enums.permission.PermissionType;
import kg.founders.core.model.dictionaries.CountryDictModel;
import kg.founders.core.services.dictionaries.CountryDictService;
import kg.founders.core.settings.security.permission.annotation.HasPermission;
import kg.founders.core.settings.security.permission.annotation.ManualPermissionControl;
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
@RequestMapping("/api/country")
@AllArgsConstructor(access = PACKAGE)
@HasPermission(value = PermissionType.PERMISSION)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CountryDictControllerRest {
    CountryDictService countryDictService;

    @GetMapping("/all")
    @ManualPermissionControl
    public List<CountryDictModel> getAll() {
        return countryDictService.getAll();
    }

    @GetMapping
    @ManualPermissionControl
    public Page<CountryDictModel> get(Pageable pageable) {
        return countryDictService.get(pageable);
    }

    @PostMapping
    @ManualPermissionControl
    public CountryDictModel create(@RequestBody CountryDictModel countryDictModel) throws Exception {
        return countryDictService.create(countryDictModel);
    }

    @PutMapping
    @ManualPermissionControl
    public CountryDictModel update(@RequestBody CountryDictModel countryDictModel) {
        return countryDictService.update(countryDictModel);
    }

    @DeleteMapping("/{id}")
    @ManualPermissionControl
    public void delete(@PathVariable Long id) {
        countryDictService.delete(id);
    }
}
