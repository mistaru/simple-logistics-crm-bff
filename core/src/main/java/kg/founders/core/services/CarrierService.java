package kg.founders.core.services;

import kg.founders.core.model.CarrierModel;
import kg.founders.core.model.CarrierProfileModel;

import java.math.BigDecimal;
import java.util.List;

public interface CarrierService {

    List<CarrierModel> getAll();

    CarrierProfileModel getById(Long id);

    CarrierModel save(CarrierModel carrier);

    CarrierModel update(CarrierModel carrier);

    void softDelete(Long id);

    List<Long> getCarrierIds();

    void updateCarrierBalance(Long id, BigDecimal amount);
}