package kg.founders.core.services.impl;

import kg.founders.core.converter.CargoConverter;
import kg.founders.core.entity.Cargo;
import kg.founders.core.enums.CargoStatus;
import kg.founders.core.model.CargoModel;
import kg.founders.core.repo.CargoRepo;
import kg.founders.core.services.CargoService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;


@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CargoServiceImpl implements CargoService {
    CargoRepo repo;
    CargoConverter converter;

    // Получение списка всех грузов
    @Override
    public List<CargoModel> findALl() {
        return repo.findAll().stream()
                .map(converter::convertFromEntity)
                .collect(Collectors.toList());
    }

    // Добавление или обновление груза
    @Override
    public CargoModel saveCargo(CargoModel cargoModel) {
        Cargo cargo = converter.convertFromModel(cargoModel);
        cargo = repo.save(cargo);
        return converter.convertFromEntity(cargo);
    }

    // Удаление груза по ID
    @Override
    public void deleteCargo(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<CargoModel> getAllActive() {
        return repo.findAllByRdtIsNullAndStatusNotIn(List.of(CargoStatus.DELIVERED_TO_CLIENT))
                .stream()
                .map(converter::convertFromEntity)
                .collect(Collectors.toList());
    }
}
