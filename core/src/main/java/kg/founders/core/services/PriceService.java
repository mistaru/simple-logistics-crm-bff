package kg.founders.core.services;

import kg.founders.core.model.PriceModel;

import java.util.List;

public interface PriceService {

    List<PriceModel> getAll();

    PriceModel getById(int id);

    PriceModel save(PriceModel model);

    PriceModel update(PriceModel model);
}