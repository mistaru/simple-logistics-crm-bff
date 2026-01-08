package kg.founders.core.services;

import kg.founders.core.model.CarrierModel;

import java.util.List;

public interface CarrierService {

    List<CarrierModel> getAll();

    CarrierModel getById(Long id);

    CarrierModel save(CarrierModel carrier);

    CarrierModel update(CarrierModel carrier);

    void softDelete(Long id);

}
