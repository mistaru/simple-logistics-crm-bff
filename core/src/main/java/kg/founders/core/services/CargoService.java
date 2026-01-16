package kg.founders.core.services;

import kg.founders.core.enums.CargoStatus;
import kg.founders.core.model.CargoModel;
import kg.founders.core.model.ReassignCargosRequest;

import java.math.BigDecimal;
import java.util.List;

public interface CargoService {
    CargoModel saveCargo(CargoModel cargoModel);

    CargoModel updateCargo(CargoModel cargoModel);

    List<CargoModel> findALl();

    void deleteCargo(Long id);

    List<CargoModel> getAllActive();

    List<CargoModel> findALlByManagerId(Long id);
    List<CargoModel> findALlByClientId(Long id);

    void reassign(Long managerId, Long cargoId);

    void reassignAll(ReassignCargosRequest reassignCargosRequest);

    boolean existsByManagerId(Long userId);

    List<Long> getCargoIds();

    CargoModel setPrice(BigDecimal price, Long cargoId);

    void updateStatusForCargos(List<Long> ids, CargoStatus status);
}
