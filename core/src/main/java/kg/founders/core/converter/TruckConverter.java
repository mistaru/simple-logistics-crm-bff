package kg.founders.core.converter;

import kg.founders.core.entity.Truck;
import kg.founders.core.model.TruckModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TruckConverter extends ModelConverter<TruckModel, Truck> {

    @PostConstruct
    public void init() {
        this.fromEntity = this::convertTruckToTruckModel;
        this.fromModel = this::convertTruckModelToTruck;
    }

    public TruckModel convertTruckToTruckModel(Truck truck) {
        if (truck == null) return null;
        TruckModel truckModel = new TruckModel();
        truckModel.setId(truck.getId());
        truckModel.setRegistrationCountry(truck.getRegistrationCountry());
        truckModel.setVolumeM3(truck.getVolumeM3());
        truckModel.setDepartureWarehouse(truck.getDepartureWarehouse());
        truckModel.setDeliveryWarehouse(truck.getDeliveryWarehouse());
        truckModel.setDriverPhone(truck.getDriverPhone());
        truckModel.setAdditionalInformation(truck.getAdditionalInformation());
        return truckModel;
    }

    public Truck convertTruckModelToTruck(TruckModel truckModel) {
        Truck truck = new Truck();
        truck.setId(truckModel.getId());
        truck.setRegistrationCountry(truckModel.getRegistrationCountry());
        truck.setVolumeM3(truckModel.getVolumeM3());
        truck.setDepartureWarehouse(truckModel.getDepartureWarehouse());
        truck.setDeliveryWarehouse(truckModel.getDeliveryWarehouse());
        truck.setDriverPhone(truckModel.getDriverPhone());
        truck.setAdditionalInformation(truckModel.getAdditionalInformation());
        return truck;
    }

}
