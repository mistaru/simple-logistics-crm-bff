package kg.founders.api.converters;

import jakarta.annotation.PostConstruct;
import kg.founders.common.converters.ModelConverter;
import kg.founders.common.entities.cargo.Cargo;
import kg.founders.common.models.cargo.CargoModel;
import org.springframework.stereotype.Component;

@Component
public class CargoConverter extends ModelConverter<CargoModel, Cargo> {
    @PostConstruct
    public void init() {
        this.fromEntity = this::convertToModel;
        this.fromModel = this::convertToEntity;
    }

    private Cargo convertToEntity(CargoModel model) {
        Cargo entity = new Cargo();
        entity.setId(model.getId());
        entity.setCreatedAt(model.getCreatedAt());
        entity.setUpdatedAt(model.getUpdatedAt());
        entity.setDescription(model.getDescription());
        entity.setWeight(model.getWeight());
        entity.setVolume(model.getVolume());
        entity.setUnitCount(model.getUnitCount());
        entity.setArrivalDate(model.getArrivalDate());
        entity.setDepartureDate(model.getDepartureDate());
        entity.setClientId(model.getClientId());
        return entity;
    }

    private CargoModel convertToModel(Cargo entity) {
        if (entity == null) return null;
        CargoModel model = new CargoModel();
        model.setId(entity.getId());
        model.setCreatedAt(entity.getCreatedAt());
        model.setUpdatedAt(entity.getUpdatedAt());
        model.setDescription(entity.getDescription());
        model.setWeight(entity.getWeight());
        model.setVolume(entity.getVolume());
        model.setUnitCount(entity.getUnitCount());
        model.setArrivalDate(entity.getArrivalDate());
        model.setDepartureDate(entity.getDepartureDate());
        model.setClientId(entity.getClientId());
        return model;
    }
}
