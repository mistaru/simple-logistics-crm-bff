package kg.founders.core.converter;

import kg.founders.core.converter.dictionaries.CityDictConverter;
import kg.founders.core.entity.Warehouse;
import kg.founders.core.model.WarehouseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class WarehouseConverter extends ModelConverter<WarehouseModel, Warehouse> {
    private final CityDictConverter cityDictConverter;

    @PostConstruct
    public void init() {
        this.fromEntity = this::convertToModel;
        this.fromModel = this::convertToEntity;
    }

    private WarehouseModel convertToModel(Warehouse entity) {
        WarehouseModel model = new WarehouseModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setLocal(entity.isLocal());
        model.setCity(cityDictConverter.convertFromEntity(entity.getCity()));
        model.setAddress(entity.getAddress());
        model.setPhoneNumber(entity.getPhoneNumber());
        model.setVolumeM3(entity.getVolumeM3());
        return model;
    }

    private Warehouse convertToEntity(WarehouseModel model) {
        Warehouse entity = new Warehouse();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setLocal(model.isLocal());
        entity.setCity(cityDictConverter.convertFromModel(model.getCity()));
        entity.setAddress(model.getAddress());
        entity.setPhoneNumber(model.getPhoneNumber());
        entity.setVolumeM3(model.getVolumeM3());
        return entity;
    }
}
