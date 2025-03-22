package kg.founders.bff.controller;

import kg.founders.core.enums.permission.PermissionType;
import kg.founders.core.model.ClientModel;
import kg.founders.core.model.dictionaries.CountryDictModel;
import kg.founders.core.services.ClientService;
import kg.founders.core.settings.security.permission.annotation.HasPermission;
import kg.founders.core.settings.security.permission.annotation.ManualPermissionControl;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@RestController
@RequestMapping("/api/client")
@AllArgsConstructor(access = PACKAGE)
@HasPermission(value = PermissionType.CLIENT)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ClientControllerRest {
    ClientService clientService;

    @GetMapping("/all")
    @ManualPermissionControl
    public List<ClientModel> getAll() {
        return clientService.getAll();
    }

    @GetMapping
    public Page<ClientModel> get(Pageable pageable) {
        return clientService.get(pageable);
    }

    @PostMapping
    public ClientModel create(@RequestBody ClientModel clientModel) throws Exception {
        return clientService.create(clientModel);
    }

    @PutMapping
    public ClientModel update(@RequestBody ClientModel clientModel) {
    return clientService.update(clientModel);
    }
}
