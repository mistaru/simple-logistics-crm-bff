package kg.founders.bff.controller;

import kg.founders.core.enums.CargoStatus;
import kg.founders.core.enums.PaymentStatus;
import kg.founders.core.model.EnumModel;
import kg.founders.core.settings.security.permission.annotation.ManualPermissionControl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/enums")
public class EnumControllerRest {

    @GetMapping("/cargoStatuses")
    @ManualPermissionControl
    public List<EnumModel> getCargoStatuses() {
        return CargoStatus.getAllToModel();
    }

    @GetMapping("/paymentStatuses")
    @ManualPermissionControl
    public List<EnumModel> getPaymentStatuses() {
        return PaymentStatus.getAllToModel();
    }
}
