package kg.founders.core.services.impl.dictionaries;

import kg.founders.core.converter.dictionaries.CityDictConverter;
import kg.founders.core.entity.dictionaries.CityDict;
import kg.founders.core.model.dictionaries.CityDictModel;
import kg.founders.core.repo.dictionaries.CityDictRepo;
import kg.founders.core.services.dictionaries.CityDictService;
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
public class CityDictServiceImpl implements CityDictService {
    CityDictRepo cityDictRepo;
    CityDictConverter cityDictConverter;

    @Override
    public List<CityDictModel> getAll() {
        return cityDictRepo.findAll().stream().map(cityDictConverter::convertFromEntity).collect(Collectors.toList());
    }

    @Override
    public Page<CityDictModel> get(Pageable pageable) {
        return cityDictRepo.findAll(pageable).map(cityDictConverter::convertFromEntity);
    }

    @Override
    public CityDictModel create(CityDictModel cityDictModel) throws Exception {
        CityDict cityDict = cityDictConverter.convertFromModel(cityDictModel);
        cityDictRepo.save(cityDict);
        return cityDictConverter.convertFromEntity(cityDict);
    }

    @Override
    public CityDictModel update(CityDictModel cityDictModel) {
        CityDict cityDict = cityDictConverter.convertFromModel(cityDictModel);
        cityDictRepo.save(cityDict);
        return cityDictConverter.convertFromEntity(cityDict);
    }

    @Override
    public void delete(Long id) {
        cityDictRepo.deleteById(id);
    }
}
