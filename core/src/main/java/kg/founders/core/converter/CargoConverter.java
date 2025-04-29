package kg.founders.core.converter;

import kg.founders.core.entity.Cargo;
import kg.founders.core.model.CargoModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class CargoConverter extends ModelConverter<CargoModel, Cargo> {
    private final ClientConverter clientConverter;

    @PostConstruct
    public void init() {
        this.fromEntity = this::convertToModel;
        this.fromModel = this::convertToEntity;
    }

    private CargoModel convertToModel(Cargo entity) {
        if (entity == null) {
            return null;
        }
        CargoModel model = new CargoModel();
        model.setId(entity.getId());
        model.setWeight(entity.getWeight());
        model.setVolume(entity.getVolume());
        model.setQuantity(entity.getQuantity());
        model.setWarehouseArrivalDate(entity.getWarehouseArrivalDate());
        model.setShipmentDate(entity.getShipmentDate());
        model.setClient(clientConverter.convertFromEntity(entity.getClient()));
        model.setStatus(entity.getStatus());
        model.setDescription(entity.getDescription());
        model.setManagerId(entity.getManagerId());
        return model;
    }

    private Cargo convertToEntity(CargoModel model) {
        Cargo cargo = new Cargo();
        cargo.setId(model.getId());
        cargo.setWeight(model.getWeight());
        cargo.setVolume(model.getVolume());
        cargo.setQuantity(model.getQuantity());
        cargo.setWarehouseArrivalDate(model.getWarehouseArrivalDate());
        cargo.setShipmentDate(model.getShipmentDate());
        cargo.setClient(clientConverter.convertFromModel(model.getClient()));
        cargo.setStatus(model.getStatus());
        cargo.setDescription(model.getDescription());
        cargo.setManagerId(model.getManagerId());
        return cargo;
    }
}
