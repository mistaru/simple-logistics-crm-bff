package kg.founders.core.services;

import kg.founders.core.model.ClientModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {

    Page<ClientModel> get(Pageable pageable);

    ClientModel create(ClientModel clientModel) throws Exception ;

    ClientModel update(ClientModel clientModel);
}
