package kg.founders.core.services.impl;

import kg.founders.core.converter.CargoConverter;
import kg.founders.core.entity.Cargo;
import kg.founders.core.entity.Client;
import kg.founders.core.enums.CargoStatus;
import kg.founders.core.model.CargoModel;
import kg.founders.core.model.ReassignCargosRequest;
import kg.founders.core.repo.CargoRepo;
import kg.founders.core.services.CargoService;
import kg.founders.core.services.ClientService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;


@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CargoServiceImpl implements CargoService {
    CargoRepo repo;
    CargoConverter cargoConverter;
    ClientService clientService;

    // Получение списка всех грузов
    @Override
    public List<CargoModel> findALl() {
        return repo.findAll().stream()
                .map(cargoConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<CargoModel> findALlByManagerId(Long id) {
        return repo.findAllByManagerId(id)
                .stream()
                .map(cargoConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    // Добавление или обновление груза
    @Override
    public CargoModel saveCargo(CargoModel cargoModel) {
        Cargo cargo = cargoConverter.convertFromModel(cargoModel);
        Client client = clientService.getClientById(cargoModel.getClient().getId());
//        if (client != null) {
        cargo.setClient(client);
//        } else {
//            client = new Client();
//            client.setClientCode(cargoModel.getClient().getClientCode());
//            client.setFullName(cargoModel.getClient().getFullName());
//            client.setPhoneNumber(cargoModel.getClient().getPhoneNumber());
//            client.setEmail(cargoModel.getClient().getEmail());
//            client.setAdditionalInfo(cargoModel.getClient().getAdditionalInfo());
//            client = clientConverter.convertFromModel(clientService.save(clientConverter.convertFromEntity(client)));
//            cargo.setClient(client);
//        }
        cargo = repo.save(cargo);
        return cargoConverter.convertFromEntity(cargo);
    }

    @Override
    @Transactional
    public CargoModel updateCargo(CargoModel model) {

        // 1. получить существующий Cargo
        Cargo cargo = repo.findById(model.getId())
                .orElseThrow(() -> new RuntimeException("Cargo not found: " + model.getId()));

        // 2. обновляем только те поля, которые приходят
        cargo.setWeight(model.getWeight());
        cargo.setVolume(model.getVolume());
        cargo.setQuantity(model.getQuantity());
        cargo.setWarehouseArrivalDate(model.getWarehouseArrivalDate());
        cargo.setShipmentDate(model.getShipmentDate());
        cargo.setStatus(model.getStatus());
        cargo.setDescription(model.getDescription());
        cargo.setManagerId(model.getManagerId());

        // 3. обновляем клиента
        Client client = clientService.getClientById(model.getClient().getId());
        cargo.setClient(client);

        // ❗ 4. ВАЖНО: НИЧЕГО НЕ ДЕЛАТЬ С cargo.getCargoTrucks()
        //    иначе Hibernate думает, что ты удалил список → orphan_remove → ошибка

        // 5. сохранить
        Cargo saved = repo.save(cargo);

        return cargoConverter.convertFromEntity(saved);
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
                .map(cargoConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void reassign(Long managerId, Long cargoId) {
        Cargo cargo = repo.findById(cargoId)
                .orElseThrow(() -> new IllegalArgumentException("Cargo with id " + cargoId + " not found"));
        cargo.setManagerId(managerId);
        repo.save(cargo);
    }

    @Override
    public void reassignAll(ReassignCargosRequest reassignCargosRequest) {
        List<Cargo> cargoList = repo.findAllByManagerId(reassignCargosRequest.getFromUserId());
        for (Cargo cargo : cargoList) {
            cargo.setManagerId(reassignCargosRequest.getToUserId());
            repo.save(cargo);
        }
    }

    @Override
    public boolean existsByManagerId(Long userId) {
        return repo.existsByManagerId(userId);
    }
}
