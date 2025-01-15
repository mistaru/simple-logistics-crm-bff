package kg.founders.api.services.impl;

import kg.founders.api.converters.CargoConverter;
import kg.founders.api.repositories.CargoRepository;
import kg.founders.api.services.CargoService;
import kg.founders.common.entities.cargo.Cargo;
import kg.founders.common.models.cargo.CargoModel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CargoServiceImpl implements CargoService {
    private final CargoRepository cargoRepository;
    private final CargoConverter cargoConverter;

    public Page<CargoModel> get(Pageable pageable) {
        return cargoRepository.findAll(pageable).map(cargoConverter::convertFromEntity);
    }

    public CargoModel create(CargoModel cargoModel) throws Exception {
        Cargo cargo = cargoConverter.convertFromModel(cargoModel);
        cargoRepository.save(cargo);
        return cargoConverter.convertFromEntity(cargo);
    }

    public CargoModel update(CargoModel cargoModel) {
        Cargo cargo = cargoConverter.convertFromModel(cargoModel);
        cargoRepository.save(cargo);
        return cargoConverter.convertFromEntity(cargo);
    }
}
