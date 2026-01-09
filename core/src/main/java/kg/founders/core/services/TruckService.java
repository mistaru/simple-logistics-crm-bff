package kg.founders.core.services;

import kg.founders.core.model.TruckModel;

import java.util.List;

public interface TruckService {

    List<TruckModel> getAll();

    TruckModel getById(int id);

    TruckModel save(TruckModel truck);

    TruckModel update(TruckModel truck);

    void softDelete(int id);

    List<Long> getTruckIds();
}
