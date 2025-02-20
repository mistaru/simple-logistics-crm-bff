package kg.founders.core.converter.dictionaries;

import kg.founders.core.converter.ModelConverter;
import kg.founders.core.entity.dictionaries.CountryDict;
import kg.founders.core.model.dictionaries.CountryDictModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class CountryDictConverter extends ModelConverter<CountryDictModel, CountryDict> {
    @PostConstruct
    public void init() {
        this.fromEntity = this::convertToModel;
        this.fromModel = this::convertToEntity;
    }

    private CountryDictModel convertToModel(CountryDict entity) {
        CountryDictModel model = new CountryDictModel();
        model.setId(entity.getId());
        return model;
    }

    private CountryDict convertToEntity(CountryDictModel model) {
        CountryDict entity = new CountryDict();
        entity.setId(model.getId());
        return entity;
    }
}
