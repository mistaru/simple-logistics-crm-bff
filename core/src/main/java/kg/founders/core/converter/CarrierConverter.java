package kg.founders.core.converter;

import kg.founders.core.entity.Carrier;
import kg.founders.core.model.CarrierModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class CarrierConverter extends ModelConverter<CarrierModel, Carrier> {

    @PostConstruct
    public void init() {

        this.fromEntity = this::convertToModel;
        this.fromModel = this::convertToEntity;

    }



    public CarrierModel convertToModel(Carrier carrier) {

        if (carrier == null) {
            return null;
        }

        CarrierModel model = new CarrierModel();
        model.setId(carrier.getId());
        model.setName(carrier.getName());
        model.setEmail(carrier.getEmail());
        model.setPhoneNumber(carrier.getPhoneNumber());
        model.setBalance(carrier.getBalance());

        return model;

    }

    public Carrier convertToEntity(CarrierModel model) {

        if (model == null) {
            return null;
        }

        Carrier carrier = new Carrier();
        carrier.setId(model.getId());
        carrier.setName(model.getName());
        carrier.setEmail(model.getEmail());
        carrier.setPhoneNumber(model.getPhoneNumber());
        carrier.setBalance(model.getBalance());

        return carrier;

    }

    public void updateCarrierFromModel(CarrierModel carrierModel, Carrier carrier) {

        carrier.setName(
                carrierModel.getName() != null ? carrierModel.getName() : carrier.getName()
        );
        carrier.setEmail(
                carrierModel.getEmail() != null ? carrierModel.getEmail() : carrier.getEmail()
        );
        carrier.setPhoneNumber(
                carrierModel.getPhoneNumber() != null ? carrierModel.getPhoneNumber() : carrier.getPhoneNumber()
        );

    }

}
