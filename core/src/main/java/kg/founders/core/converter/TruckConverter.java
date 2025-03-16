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
        truckModel.setVolumeTotalM3(truck.getVolumeTotalM3());
        truckModel.setVolumeOccupiedM3(truck.getVolumeOccupiedM3());
        truckModel.setVolumeAvailableM3(truck.getVolumeAvailableM3());
        truckModel.setDepartureWarehouse(truck.getDepartureWarehouse());
        truckModel.setArrivalWarehouse(truck.getArrivalWarehouse());
        truckModel.setDriverFullname(truck.getDriverFullname());
        truckModel.setDriverPhone(truck.getDriverPhone());
        truckModel.setDepartureDatePlanned(truck.getDepartureDatePlanned());
        truckModel.setDepartureDateActual(truck.getDepartureDateActual());
        truckModel.setArrivalDatePlanned(truck.getArrivalDatePlanned());
        truckModel.setArrivalDateActual(truck.getArrivalDateActual());
        truckModel.setAdditionalInformation(truck.getAdditionalInformation());
        return truckModel;
    }

    public Truck convertTruckModelToTruck(TruckModel truckModel) {
        Truck truck = new Truck();
        truck.setId(truckModel.getId());
        truck.setRegistrationCountry(truckModel.getRegistrationCountry());
        truck.setVolumeTotalM3(truckModel.getVolumeTotalM3());
        truck.setVolumeOccupiedM3(truckModel.getVolumeOccupiedM3());
        truck.setVolumeAvailableM3(truckModel.getVolumeAvailableM3());
        truck.setDepartureWarehouse(truckModel.getDepartureWarehouse());
        truck.setArrivalWarehouse(truckModel.getArrivalWarehouse());
        truck.setDriverFullname(truckModel.getDriverFullname());
        truck.setDriverPhone(truckModel.getDriverPhone());
        truck.setDepartureDatePlanned(truckModel.getDepartureDatePlanned());
        truck.setDepartureDateActual(truckModel.getDepartureDateActual());
        truck.setArrivalDatePlanned(truckModel.getArrivalDatePlanned());
        truck.setArrivalDateActual(truckModel.getArrivalDateActual());
        truck.setAdditionalInformation(truckModel.getAdditionalInformation());
        return truck;
    }

}
