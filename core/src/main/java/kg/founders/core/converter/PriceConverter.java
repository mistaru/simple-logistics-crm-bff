package kg.founders.core.converter;

import kg.founders.core.entity.Cargo;
import kg.founders.core.entity.Price;
import kg.founders.core.model.PriceModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class PriceConverter extends ModelConverter<PriceModel, Price> {
    private final CargoConverter cargoConverter;

    @PostConstruct
    public void init() {
        this.fromEntity = this::convertPriceToPriceModel;
        this.fromModel = this::convertPriceModelToPrice;
    }

    public PriceModel convertPriceToPriceModel(Price price) {
        if (price == null) return null;
        PriceModel model = new PriceModel();
        model.setId(price.getId());
        model.setAmount(price.getAmount());
        model.setCargo(cargoConverter.convertFromEntity(price.getCargo()));
        return model;
    }

    public Price convertPriceModelToPrice(PriceModel model) {
        Price price = new Price();
        price.setId(model.getId());
        price.setAmount(model.getAmount());
        price.setCargo(new Cargo(model.getCargo().getId()));
        return price;
    }
}