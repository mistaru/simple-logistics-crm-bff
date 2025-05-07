package kg.founders.core.services.impl;

import kg.founders.core.converter.PriceConverter;
import kg.founders.core.entity.Price;
import kg.founders.core.model.PriceModel;
import kg.founders.core.repo.PriceRepository;
import kg.founders.core.services.PriceService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PriceServiceImpl implements PriceService {

    PriceRepository repository;
    PriceConverter converter;

    @Override
    public List<PriceModel> getAll() {
        return repository.findAll().stream()
                .filter(truck -> truck.getRdt() == null)
                .map(converter::convertPriceToPriceModel).collect(Collectors.toList());
    }

    @Override
    public PriceModel getById(int id) {
        return converter.convertPriceToPriceModel(repository.findById((long) id).orElse(null));
    }

    @Override
    public PriceModel save(PriceModel model) {
        Price price = converter.convertPriceModelToPrice(model);
        repository.save(price);
        return converter.convertPriceToPriceModel(price);
    }

    @Override
    public PriceModel update(PriceModel model) {
        Optional<Price> oldPrice = repository.findById(model.getId());
        Price newPrice = converter.convertPriceModelToPrice(model);
        newPrice.setId(oldPrice.get().getId());
        repository.save(newPrice);

        return converter.convertPriceToPriceModel(newPrice);
    }
}