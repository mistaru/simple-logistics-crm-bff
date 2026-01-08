package kg.founders.core.converter;

import kg.founders.core.entity.Carrier;
import kg.founders.core.model.CarrierModel;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@Component
public class CarrierConverter extends ModelConverter<CarrierModel, Carrier> {

    private final TruckConverter truckConverter;

    public CarrierConverter(@Lazy TruckConverter truckConverter) {
        this.truckConverter = truckConverter;
    }

    @PostConstruct
    public void init() {

        this.fromEntity = this::convertToModel;
        this.fromModel = this::convertToEntity;

    }

    public CarrierModel convertToModel(Carrier carrier) {

        if (carrier == null) {
            return null;
        }

        CarrierModel model = convertToModelSimple(carrier);

        if (carrier.getTrucks() != null) {
            model.setTrucks(carrier.getTrucks().stream()
                    .map(truckConverter::convertTruckToTruckModel)
                    .collect(Collectors.toList()));
        }

        return model;

    }

    public CarrierModel convertToModelSimple(Carrier carrier) {
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

        if (model.getTrucks() != null) {
            carrier.setTrucks(model.getTrucks().stream()
                    .map(truckConverter::convertTruckModelToTruck)
                    .collect(Collectors.toList()));
        }

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
