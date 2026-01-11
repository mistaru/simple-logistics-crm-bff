package kg.founders.bff.controller;

import kg.founders.core.enums.PaymentStatus;
import kg.founders.core.enums.permission.PermissionType;
import kg.founders.core.exceptions.ForbiddenException;
import kg.founders.core.model.CargoPaymentModel;
import kg.founders.core.model.PaymentModel;
import kg.founders.core.model.PaymentModelUpd;
import kg.founders.core.services.PaymentService;
import kg.founders.core.settings.security.permission.annotation.HasPermission;
import kg.founders.core.settings.security.permission.annotation.ManualPermissionControl;
import kg.founders.core.util.PermissionHelper;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@RestController
@RequestMapping("/api/payment")
@AllArgsConstructor(access = PACKAGE)
@HasPermission(value = PermissionType.PERMISSION)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PaymentControllerRest {

    PaymentService paymentService;
    PermissionHelper permissionHelper;

    @GetMapping
    public List<PaymentModel> getAll() {
        if (permissionHelper.isAdmin()) return paymentService.getAll();
        else return paymentService.findAllByManagerId(permissionHelper.currentUserId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentModel> getById(@PathVariable Long id) {
        PaymentModel paymentModel = paymentService.getById(id);
        if (paymentModel == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(paymentModel);
    }

    @PostMapping("/cargo")
    public PaymentModel createCargo(@RequestBody PaymentModel paymentModel) {
        paymentModel.setManagerId(permissionHelper.currentUserId());
        paymentModel.setStatus(PaymentStatus.CLIENT_PAYS_FOR_CARGO);
        return paymentService.save(paymentModel);
    }

    @PostMapping("/truck")
    public PaymentModel createTruck(@RequestBody PaymentModel paymentModel) {
        paymentModel.setManagerId(permissionHelper.currentUserId());
        paymentModel.setStatus(PaymentStatus.COMPANY_PAYS_CARRIERS);
        return paymentService.save(paymentModel);
    }

    @PutMapping
    public PaymentModel update(@RequestBody PaymentModelUpd paymentModel) {
        return paymentService.update(paymentModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        paymentService.delete(id);
    }

    @GetMapping("/get-all-cargo-payment-models")
    public List<CargoPaymentModel> getAllGCargoPaymentModels() {
        return paymentService.getAllCargoPayments();
    }

    @PostMapping("/reassign")
    @ManualPermissionControl
    public ResponseEntity<Void> reassign(@RequestBody Long managerId, @RequestBody Long paymentId) {
        if (!permissionHelper.isAdmin()) throw new ForbiddenException();
        paymentService.reassign(managerId, paymentId);
        return ResponseEntity.ok().build();
    }
}