package kg.founders.core.services;

import kg.founders.core.entity.Client;
import kg.founders.core.model.ClientModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {

    Page<ClientModel> get(Pageable pageable);

    ClientModel create(ClientModel clientModel) throws Exception ;

    ClientModel update(ClientModel clientModel);

    List<ClientModel> getAll();

    ClientModel save(ClientModel model);

    Client getClientById(Long id);

    List<ClientModel> findALlByManagerId(Long userId);
}
