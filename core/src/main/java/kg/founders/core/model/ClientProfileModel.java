package kg.founders.core.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientProfileModel {
    Long id;
    String fullName;
    String clientCode;
    String phoneNumber;
    String whatsappNumber;
    String email;
    String additionalInfo;
    Long managerId;

    BigDecimal totalInvoiceTotal;
    BigDecimal totalPaymentReceived;
    BigDecimal totalBalanceDue;

    List<CargoProfileModel> cargoProfiles;
}
