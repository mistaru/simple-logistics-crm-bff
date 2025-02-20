package kg.founders.core.services.impl.dictionaries;

import kg.founders.core.converter.dictionaries.CountryDictConverter;
import kg.founders.core.entity.dictionaries.CountryDict;
import kg.founders.core.model.dictionaries.CountryDictModel;
import kg.founders.core.repo.dictionaries.CountryDictRepo;
import kg.founders.core.services.dictionaries.CountryDictService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CountryDictServiceImpl implements CountryDictService {
    CountryDictRepo countryDictRepo;
    CountryDictConverter countryDictConverter;

    @Override
    public List<CountryDictModel> getAll() {
        return countryDictRepo.findAll().stream().map(countryDictConverter::convertFromEntity).collect(Collectors.toList());
    }

    @Override
    public Page<CountryDictModel> get(Pageable pageable) {
        return countryDictRepo.findAll(pageable).map(countryDictConverter::convertFromEntity);
    }

    @Override
    public CountryDictModel create(CountryDictModel countryDictModel) throws Exception {
        CountryDict countryDict = countryDictConverter.convertFromModel(countryDictModel);
        countryDictRepo.save(countryDict);
        return countryDictConverter.convertFromEntity(countryDict);
    }

    @Override
    public CountryDictModel update(CountryDictModel countryDictModel) {
        CountryDict countryDict = countryDictConverter.convertFromModel(countryDictModel);
        countryDictRepo.save(countryDict);
        return countryDictConverter.convertFromEntity(countryDict);
    }
}
