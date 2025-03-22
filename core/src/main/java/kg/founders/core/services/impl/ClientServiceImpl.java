package kg.founders.core.services.impl;

import kg.founders.core.converter.ClientConverter;
import kg.founders.core.entity.Client;
import kg.founders.core.model.ClientModel;
import kg.founders.core.model.dictionaries.CountryDictModel;
import kg.founders.core.repo.ClientRepo;
import kg.founders.core.services.ClientService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ClientServiceImpl implements ClientService {
    ClientRepo clientRepo;
    ClientConverter clientConverter;

    @Override
    public Page<ClientModel> get(Pageable pageable) {
        return clientRepo.findAll(pageable).map(clientConverter::convertFromEntity);
    }

    @Override
    public ClientModel create(ClientModel clientModel) throws Exception {
        Client client = clientConverter.convertFromModel(clientModel);
        clientRepo.save(client);
        return clientConverter.convertFromEntity(client);
    }

    @Override
    public ClientModel update(ClientModel clientModel) {
        Client client = clientConverter.convertFromModel(clientModel);
        clientRepo.save(client);
        return clientConverter.convertFromEntity(client);
    }

    @Override
    public List<ClientModel> getAll() {
        return clientRepo.findAll().stream().map(clientConverter::convertFromEntity).collect(Collectors.toList());
    }
}