package kg.founders.core.services.impl;

import kg.founders.core.converter.CarrierConverter;
import kg.founders.core.entity.Carrier;
import kg.founders.core.entity.Truck;
import kg.founders.core.enums.PaymentStatus;
import kg.founders.core.model.*;
import kg.founders.core.repo.CarrierRepository;
import kg.founders.core.repo.TruckRepository;
import kg.founders.core.services.CargoTruckService;
import kg.founders.core.services.CarrierService;
import kg.founders.core.services.PaymentService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
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
    CargoTruckService cargoTruckService;
    PaymentService paymentService;


    @Override
    @Transactional(readOnly = true)
    public List<CarrierModel> getAll() {

        List<Carrier> carriers = carrierRepository.findAllWithTrucks();

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
    @Transactional(readOnly = true)
    public CarrierProfileModel getById(Long id) {
        Carrier carrier = carrierRepository.findWithTrucksById(id).orElse(null);
        if (carrier == null) {
            return null;
        }
        CarrierProfileModel model = new CarrierProfileModel(carrierConverter.convertToModel(carrier));
        //model.setBalance(truckRepository.calculateServiceFeeForCarrier(id)); // TODO get balance from payment

        List<CargoModel> cargos = new ArrayList<>();

        // 1. Получаем все cargoId и все cargo перевозчика
        List<Long> cargoIds = new ArrayList<>();
        for (Truck truck : carrier.getTrucks()) {

            CargoTruckModel cargoTruckModel = cargoTruckService.getCargoTruckByTruckId(truck.getId());

            if (cargoTruckModel != null && cargoTruckModel.getCargos() != null) {
                cargos.addAll(cargoTruckModel.getCargos());
                cargoTruckModel.getCargos().forEach(cargoModel -> {
                    if (cargoModel != null && cargoModel.getId() != null) {
                        cargoIds.add(cargoModel.getId());
                    }
                });
            }
        }

        // 2) Один запрос на все платежи по грузам клиента
        //    и агрегация суммы платежей по cargoId
        Map<Long, BigDecimal> paidByCargoId = aggregatePaidByCargoId(cargoIds);

        // 3) Формируем информацию по платежам перевозчика
        BigDecimal carrierInvoiceTotal = BigDecimal.ZERO;
        BigDecimal carrierPaymentReceived = BigDecimal.ZERO;
        BigDecimal carrierBalanceDue = BigDecimal.ZERO;

        for (CargoModel cargo : cargos) {
            BigDecimal invoiceTotal = safe(cargo.getPrice()); // цена груза
            BigDecimal paymentReceived = safe(paidByCargoId.get(cargo.getId())); // сколько оплатили по этому грузу
            BigDecimal balanceDue = invoiceTotal.subtract(paymentReceived); // сколько осталось

            //cargoProfiles.add(toCargoProfile(cargo, invoiceTotal, paymentReceived, balanceDue));

            // ИТОГО по перевозчику
            carrierInvoiceTotal = carrierInvoiceTotal.add(invoiceTotal);
            carrierPaymentReceived = carrierPaymentReceived.add(paymentReceived);
        }

        carrierBalanceDue = carrierInvoiceTotal.subtract(carrierPaymentReceived);

        model.setTotalInvoiceTotal(carrierInvoiceTotal);
        model.setTotalPaymentReceived(carrierPaymentReceived);
        model.setTotalBalanceDue(carrierBalanceDue);

        return model;
    }

    @Override
    @Transactional
    public CarrierModel save(CarrierModel carrierModel) {
        Carrier carrier = carrierConverter.convertToEntity(carrierModel);
        carrierRepository.save(carrier);
        return carrierConverter.convertToModel(carrier);
    }

    @Override
    @Transactional
    public CarrierModel update(CarrierModel carrierModel) {
        Carrier existingCarrier = carrierRepository.findById(carrierModel.getId())
                .orElseThrow(() -> new RuntimeException("Carrier not found"));

        carrierConverter.updateCarrierFromModel(carrierModel, existingCarrier);

        Carrier savedCarrier = carrierRepository.save(existingCarrier);
        return carrierConverter.convertToModel(savedCarrier);

    }

    @Override
    @Transactional
    public void softDelete(Long id) {
        Optional<Carrier> carrier = carrierRepository.findById(id);

        if (carrier.isPresent()) {
            Carrier carrierToDelete = carrier.get();
            carrierToDelete.markDeleted();
            carrierRepository.save(carrierToDelete);
        }
    }

    /**
     * Агрегирует платежи (сумму amount) по cargoId.
     */
    private Map<Long, BigDecimal> aggregatePaidByCargoId(List<Long> cargoIds) {
        if (cargoIds == null || cargoIds.isEmpty()) {
            return Collections.emptyMap();
        }

        List<PaymentModel> payments = paymentService.findAllByPayerIdsAndPaymentStatus(
                cargoIds,
                PaymentStatus.COMPANY_PAYS_CARRIERS
        );

        if (payments == null || payments.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<Long, BigDecimal> paidByCargoId = new HashMap<>();

        for (PaymentModel p : payments) {
            Long cargoId = p.getCargo().getId();
            if (cargoId == null) continue;

            BigDecimal amount = safe(p.getAmount());
            paidByCargoId.merge(cargoId, amount, BigDecimal::add);
        }

        return paidByCargoId;
    }

    /**
     * Безопасное приведение null -> BigDecimal.ZERO
     */
    private BigDecimal safe(BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value;
    }

    @Override
    public List<Long> getCarrierIds() {
        return carrierRepository.findIdByRdtIsNull();
    }
}