package kg.founders.api.converters.dictionaries;

import jakarta.annotation.PostConstruct;
import kg.founders.common.converters.ModelConverter;
import kg.founders.common.entities.dictionaries.CountryDict;
import kg.founders.common.models.dictionaries.countryDict.CountryDictModel;
import org.springframework.stereotype.Component;

@Component
public class CountryDictConverter extends ModelConverter<CountryDictModel, CountryDict> {
    @PostConstruct
    public void init() {
        this.fromEntity = this::convertToModel;
        this.fromModel = this::convertToEntity;
    }

    private CountryDict convertToEntity(CountryDictModel model) {
        CountryDict entity = new CountryDict();
        entity.setId(model.getId());
        entity.setCreatedAt(model.getCreatedAt());
        entity.setUpdatedAt(model.getUpdatedAt());
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        return entity;
    }

    private CountryDictModel convertToModel(CountryDict entity) {
        if (entity == null) return null;
        CountryDictModel model = new CountryDictModel();
        model.setId(entity.getId());
        model.setCreatedAt(entity.getCreatedAt());
        model.setUpdatedAt(entity.getUpdatedAt());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        return model;
    }
}
