package kg.founders.core.services.impl;

import kg.founders.core.converter.CarrierConverter;
import kg.founders.core.entity.Carrier;
import kg.founders.core.model.CarrierModel;
import kg.founders.core.repo.CarrierRepository;
import kg.founders.core.repo.TruckRepository;
import kg.founders.core.services.CarrierService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CarrierServiceImpl implements CarrierService {

    CarrierRepository carrierRepository;
    CarrierConverter carrierConverter;
    private final TruckRepository truckRepository;

    @Override
    public List<CarrierModel> getAll() {

        List<Carrier> carriers = carrierRepository.findAll().stream()
                .filter(carrier -> carrier.getRdt() == null).collect(Collectors.toList());

        List<Long> carrierIds = carriers.stream().map(Carrier::getId).collect(Collectors.toUnmodifiableList());

        // Fetch all balances in one query
        Map<Long, BigDecimal> balances = truckRepository.getBalancesForCarriers(carrierIds);

        // Map to models and set the balance
        return carriers.stream()
                .map(carrier -> {
                   CarrierModel model = carrierConverter.convertToModel(carrier);
                   // Set balance from the map, defaulting to zero if not present
                    model.setBalance(balances.getOrDefault(carrier.getId(), BigDecimal.ZERO));
                    return model;
                }).collect(Collectors.toList());
    }

    @Override
    public CarrierModel getById(Long id) {
        Carrier carrier = carrierRepository.findById(id).orElse(null);
        if (carrier == null) {
            return null;
        }
        CarrierModel model = carrierConverter.convertToModel(carrier);
        model.setBalance(truckRepository.calculateServiceFeeForCarrier(id));
        return model;
    }

    @Override
    public CarrierModel save(CarrierModel carrierModel) {
        Carrier carrier = carrierConverter.convertToEntity(carrierModel);
        carrierRepository.save(carrier);
        return carrierConverter.convertToModel(carrier);
    }

    @Override
    public CarrierModel update(CarrierModel carrierModel) {
        Carrier existingCarrier = carrierRepository.findById(carrierModel.getId())
                .orElseThrow(() -> new RuntimeException("Carrier not found"));

        carrierConverter.updateCarrierFromModel(carrierModel, existingCarrier);

        Carrier savedCarrier = carrierRepository.save(existingCarrier);
        return carrierConverter.convertToModel(savedCarrier);

    }

    @Override
    public void softDelete(Long id) {
        Optional<Carrier> carrier = carrierRepository.findById(id);

        if (carrier.isPresent()) {
            Carrier carrierToDelete = carrier.get();
            carrierToDelete.markDeleted();
            carrierRepository.save(carrierToDelete);
        }
    }

}
