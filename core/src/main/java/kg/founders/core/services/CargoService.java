package kg.founders.core.services;

import kg.founders.core.model.CargoModel;
import kg.founders.core.model.ReassignCargosRequest;

import java.util.List;

public interface CargoService {
    CargoModel saveCargo(CargoModel cargoModel);

    CargoModel updateCargo(CargoModel cargoModel);

    List<CargoModel> findALl();

    void deleteCargo(Long id);

    List<CargoModel> getAllActive();

    List<CargoModel> findALlByManagerId(Long id);

    void reassign(Long managerId, Long cargoId);

    void reassignAll(ReassignCargosRequest reassignCargosRequest);

    boolean existsByManagerId(Long userId);
}
