package kg.founders.api.controllers.dictionaries;

import kg.founders.api.services.dictionaries.CountryDictService;
import kg.founders.common.models.dictionaries.countryDict.CountryDictModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dict/country-dict")
@RequiredArgsConstructor
public class CountryDictController {
    private final CountryDictService countryDictService;

    @GetMapping
    public Page<CountryDictModel> get(Pageable pageable) {
        return countryDictService.get(pageable);
    }

    @PostMapping
    public CountryDictModel create(@RequestBody CountryDictModel countryDictModel) throws Exception {
        return countryDictService.create(countryDictModel);
    }

    @PutMapping
    public CountryDictModel update(@RequestBody CountryDictModel countryDictModel) {
        return countryDictService.update(countryDictModel);
    }
}
