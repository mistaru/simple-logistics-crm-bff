package kg.founders.core.converter;

import kg.founders.core.entity.Truck;
import kg.founders.core.model.TruckModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
        truck.setRegistrationCountry(
                truckModel.getRegistrationCountry() != null ? truckModel.getRegistrationCountry() : "Не указано"
        );
        truck.setVolumeTotalM3(
                truckModel.getVolumeTotalM3() != null ? truckModel.getVolumeTotalM3() : 0.0
        );
        truck.setVolumeOccupiedM3(
                truckModel.getVolumeOccupiedM3() != null ? truckModel.getVolumeOccupiedM3() : 0.0
        );
        truck.setVolumeAvailableM3(
                truckModel.getVolumeAvailableM3() != null ? truckModel.getVolumeAvailableM3() : 0.0
        );
        truck.setDepartureWarehouse(
                truckModel.getDepartureWarehouse() != null ? truckModel.getDepartureWarehouse() : "Не указан"
        );
        truck.setArrivalWarehouse(
                truckModel.getArrivalWarehouse() != null ? truckModel.getArrivalWarehouse() : "Не указан"
        );
        truck.setDriverFullname(
                truckModel.getDriverFullname() != null ? truckModel.getDriverFullname() : "Неизвестный водитель"
        );
        truck.setDriverPhone(
                truckModel.getDriverPhone() != null ? truckModel.getDriverPhone() : "Не указан"
        );

        LocalDateTime now = LocalDateTime.now();

        truck.setDepartureDatePlanned(
                truckModel.getDepartureDatePlanned() != null ? truckModel.getDepartureDatePlanned() : Timestamp.valueOf(now)
        );
        truck.setDepartureDateActual(
                truckModel.getDepartureDateActual() != null ? truckModel.getDepartureDateActual() : Timestamp.valueOf(now)
        );
        truck.setArrivalDatePlanned(
                truckModel.getArrivalDatePlanned() != null ? truckModel.getArrivalDatePlanned() : Timestamp.valueOf(now)
        );
        truck.setArrivalDateActual(
                truckModel.getArrivalDateActual() != null ? truckModel.getArrivalDateActual() : Timestamp.valueOf(now)
        );

        truck.setAdditionalInformation(
                truckModel.getAdditionalInformation() != null ? truckModel.getAdditionalInformation() : "Нет дополнительной информации"
        );

        return truck;
    }

}
