package kg.founders.core.services.impl;

import kg.founders.core.entity.Cargo;
import kg.founders.core.enums.CargoStatus;
import kg.founders.core.model.CargoModel;
import kg.founders.core.repo.CargoRepo;
import kg.founders.core.services.CargoService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;


@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CargoServiceImpl implements CargoService {
    CargoRepo repo;

    // Получение списка всех грузов
    public List<CargoModel> findALl() {
        return repo.findAll().stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    // Добавление или обновление груза
    public CargoModel saveCargo(CargoModel cargoModel) {
        Cargo cargo = convertToEntity(cargoModel);
        cargo = repo.save(cargo);
        return convertToModel(cargo);
    }

    // Удаление груза по ID
    public void deleteCargo(Long id) {
        repo.deleteById(id);
    }

    // Преобразование из Entity в Model
    private CargoModel convertToModel(Cargo cargo) {
        return new CargoModel(
                cargo.getId(),
                cargo.getWeight(),
                cargo.getVolume(),
                cargo.getQuantity(),
                cargo.getWarehouseArrivalDate(),
                cargo.getShipmentDate(),
                null, //cargo.getClient().toModel(), временное решение пока не засетим клиента
                cargo.getStatus(),
                cargo.getDescription()
        );
    }

    @Override
    public List<CargoModel> getAllActive() {
        return repo.findAllByRdtIsNullAndStatusNotIn(List.of(CargoStatus.DELIVERED_TO_CLIENT))
                .stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    // Преобразование из Model в Entity
    private Cargo convertToEntity(CargoModel model) {
        Cargo cargo = new Cargo();
        cargo.setId(model.getId());
        cargo.setWeight(model.getWeight());
        cargo.setVolume(model.getVolume());
        cargo.setQuantity(model.getQuantity());
        cargo.setWarehouseArrivalDate(model.getWarehouseArrivalDate());
        cargo.setShipmentDate(model.getShipmentDate());
        cargo.setStatus(model.getStatus());
        cargo.setDescription(model.getDescription());
        return cargo;
    }
}
