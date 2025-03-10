package kg.founders.core.converter.dictionaries;

import kg.founders.core.converter.ModelConverter;
import kg.founders.core.entity.dictionaries.CityDict;
import kg.founders.core.model.dictionaries.CityDictModel;
import kg.founders.core.model.dictionaries.CountryDictModel;
import liquibase.pro.packaged.C;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class CityDictConverter extends ModelConverter<CityDictModel, CityDict> {
    private final CountryDictConverter countryDictConverter;

    @PostConstruct
    public void init() {
        this.fromEntity = this::convertToModel;
        this.fromModel = this::convertToEntity;
    }

    private CityDictModel convertToModel(CityDict entity) {
        CityDictModel model = new CityDictModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        if (entity.getCountry() != null)
            model.setCountry(countryDictConverter.convertFromEntity(entity.getCountry()));
        return model;
    }

    private CityDict convertToEntity(CityDictModel model) {
        CityDict entity = new CityDict();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        if (model.getCountry() != null)
            entity.setCountry(countryDictConverter.convertFromModel(model.getCountry()));
        return entity;
    }
}
