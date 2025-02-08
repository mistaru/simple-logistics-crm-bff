package kg.founders.api.services.dictionaries.impl;

import kg.founders.api.converters.dictionaries.CountryDictConverter;
import kg.founders.api.repositories.dictionaries.CountryDictRepository;
import kg.founders.api.services.dictionaries.CountryDictService;
import kg.founders.common.entities.dictionaries.CountryDict;
import kg.founders.common.models.dictionaries.countryDict.CountryDictModel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CountryDictServiceImpl implements CountryDictService {
    private final CountryDictRepository countryDictRepository;
    private final CountryDictConverter countryDictConverter;

    public Page<CountryDictModel> get(Pageable pageable) {
        return countryDictRepository.findAll(pageable).map(countryDictConverter::convertFromEntity);
    }

    public CountryDictModel create(CountryDictModel countryDictModel) throws Exception {
        CountryDict countryDict = countryDictConverter.convertFromModel(countryDictModel);
        countryDictRepository.save(countryDict);
        return countryDictConverter.convertFromEntity(countryDict);
    }

    public CountryDictModel update(CountryDictModel countryDictModel) {
        CountryDict countryDict = countryDictConverter.convertFromModel(countryDictModel);
        countryDictRepository.save(countryDict);
        return countryDictConverter.convertFromEntity(countryDict);
    }
}
