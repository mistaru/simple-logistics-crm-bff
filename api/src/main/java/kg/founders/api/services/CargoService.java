package kg.founders.api.services;

import kg.founders.common.models.cargo.CargoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CargoService {
    Page<CargoModel> get(Pageable pageable);

    CargoModel create(CargoModel cargoModel) throws Exception ;

    CargoModel update(CargoModel cargoModel);
}
