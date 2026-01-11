package kg.founders.core.services.impl;

import kg.founders.core.enums.PaymentStatus;
import kg.founders.core.model.*;
import kg.founders.core.services.CargoService;
import kg.founders.core.services.ClientProfileService;
import kg.founders.core.services.ClientService;
import kg.founders.core.services.PaymentService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ClientProfileServiceImpl implements ClientProfileService {

    ClientService clientService;
    CargoService cargoService;
    PaymentService paymentService;

    /**
     * Возвращает профили всех клиентов.
     */
    @Override
    public List<ClientProfileModel> getAll() {
        return buildProfiles(clientService.getAll());
    }

    /**
     * Возвращает профили клиентов конкретного менеджера.
     */
    @Override
    public List<ClientProfileModel> findALlByManagerId(Long currentUserId) {
        return buildProfiles(clientService.findALlByManagerId(currentUserId));
    }

    /**
     * Метод сборки профилей
     */
    private List<ClientProfileModel> buildProfiles(List<ClientModel> clients) {
        if (clients == null || clients.isEmpty()) {
            return List.of();
        }

        List<ClientProfileModel> result = new ArrayList<>(clients.size());

        for (ClientModel client : clients) {
            // 1) Грузы клиента
            List<CargoModel> cargos = cargoService.findALlByClientId(client.getId());
            if (cargos == null) cargos = List.of();

            // 2) Собираем cargoIds (для bulk запроса платежей)
            List<Long> cargoIds = cargos.stream()
                    .map(CargoModel::getId)
                    .filter(Objects::nonNull)
                    .toList();

            // 3) Один запрос на все платежи по грузам клиента
            //    и агрегация суммы платежей по cargoId
            Map<Long, BigDecimal> paidByCargoId = aggregatePaidByCargoId(cargoIds);

            // 4) Собираем cargoProfileModels + одновременно считаем totals по клиенту
            BigDecimal clientInvoiceTotal = BigDecimal.ZERO;
            BigDecimal clientPaymentReceived = BigDecimal.ZERO;

            List<CargoProfileModel> cargoProfiles = new ArrayList<>(cargos.size());

            for (CargoModel cargo : cargos) {
                BigDecimal invoiceTotal = safe(cargo.getPrice()); // цена груза
                BigDecimal paymentReceived = safe(paidByCargoId.get(cargo.getId())); // сколько оплатили по этому грузу
                BigDecimal balanceDue = invoiceTotal.subtract(paymentReceived); // сколько осталось

                cargoProfiles.add(toCargoProfile(cargo, invoiceTotal, paymentReceived, balanceDue));

                // ИТОГО по клиенту
                clientInvoiceTotal = clientInvoiceTotal.add(invoiceTotal);
                clientPaymentReceived = clientPaymentReceived.add(paymentReceived);
            }

            BigDecimal clientBalanceDue = clientInvoiceTotal.subtract(clientPaymentReceived);

            result.add(toClientProfile(client, clientInvoiceTotal, clientPaymentReceived, clientBalanceDue, cargoProfiles));
        }

        return result;
    }

    /**
     * Агрегирует платежи (сумму amount) по cargoId.
     */
    private Map<Long, BigDecimal> aggregatePaidByCargoId(List<Long> cargoIds) {
        if (cargoIds == null || cargoIds.isEmpty()) {
            return Collections.emptyMap();
        }

        List<PaymentModel> payments = paymentService.findAllByCargoIdsAndPaymentStatus(
                cargoIds,
                PaymentStatus.CLIENT_PAYS_FOR_CARGO
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

    private CargoProfileModel toCargoProfile(CargoModel cargo,
                                             BigDecimal invoiceTotal,
                                             BigDecimal paymentReceived,
                                             BigDecimal balanceDue) {
        return new CargoProfileModel(
                cargo.getId(),
                cargo.getWeight(),
                cargo.getVolume(),
                cargo.getQuantity(),
                cargo.getWarehouseArrivalDate(),
                cargo.getShipmentDate(),
                cargo.getStatus(),
                cargo.getDescription(),
                cargo.getManagerId(),
                cargo.getPrice(),
                invoiceTotal,
                paymentReceived,
                balanceDue
        );
    }

    private ClientProfileModel toClientProfile(ClientModel client,
                                               BigDecimal invoiceTotal,
                                               BigDecimal paymentReceived,
                                               BigDecimal balanceDue,
                                               List<CargoProfileModel> cargoProfiles) {
        return new ClientProfileModel(
                client.getId(),
                client.getFullName(),
                client.getClientCode(),
                client.getPhoneNumber(),
                client.getWhatsappNumber(),
                client.getEmail(),
                client.getAdditionalInfo(),
                client.getManagerId(),
                invoiceTotal,
                paymentReceived,
                balanceDue,
                cargoProfiles
        );
    }
}
