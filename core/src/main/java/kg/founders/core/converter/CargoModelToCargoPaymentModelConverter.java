package kg.founders.core.converter;

import kg.founders.core.entity.Payment;
import kg.founders.core.model.CargoModel;
import kg.founders.core.model.CargoPaymentModel;
import kg.founders.core.model.PaymentModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CargoModelToCargoPaymentModelConverter extends ModelConverter<CargoModel, CargoPaymentModel> {
    public CargoPaymentModel convertCargoToCargoPaymentModel(CargoModel cargoModel) {
        if (cargoModel == null) return null;
        CargoPaymentModel cargoPaymentModel = new CargoPaymentModel();
        cargoPaymentModel.setId(cargoPaymentModel.getId());
        cargoPaymentModel.setClientFullName(cargoModel.getClientModel().getFullName());
        cargoPaymentModel.setStatus(cargoModel.getStatus());
        cargoPaymentModel.setShipmentDate(cargoModel.getShipmentDate());
        cargoPaymentModel.setWarehouseArrivalDate(cargoModel.getWarehouseArrivalDate());
        cargoPaymentModel.setDescription(cargoModel.getDescription());

        return cargoPaymentModel;
    }
}
