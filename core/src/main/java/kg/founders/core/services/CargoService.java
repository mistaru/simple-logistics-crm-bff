package kg.founders.core.services;

import kg.founders.core.model.CargoModel;

import java.util.List;

public interface CargoService {
    CargoModel saveCargo(CargoModel cargoModel);
    List<CargoModel> findALl();
    void deleteCargo(Long id);
    List<CargoModel> getAllActive();

}
