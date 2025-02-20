package kg.founders.core.converter;

import kg.founders.core.entity.Client;
import kg.founders.core.model.ClientModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class ClientConverter extends ModelConverter<ClientModel, Client> {
    @PostConstruct
    public void init() {
        this.fromEntity = this::convertToModel;
        this.fromModel = this::convertToEntity;
    }

    private ClientModel convertToModel(Client entity) {
        ClientModel model = new ClientModel();
        model.setId(entity.getId());
        model.setFullName(entity.getFullName());
        model.setPhoneNumber(entity.getPhoneNumber());
        model.setWhatsappNumber(entity.getWhatsappNumber());
        model.setEmail(entity.getEmail());
        model.setAdditionalInfo(entity.getAdditionalInfo());
        return model;
    }

    private Client convertToEntity(ClientModel model) {
        Client entity = new Client();
        entity.setId(model.getId());
        entity.setFullName(model.getFullName());
        entity.setPhoneNumber(model.getPhoneNumber());
        entity.setWhatsappNumber(model.getWhatsappNumber());
        entity.setEmail(model.getEmail());
        entity.setAdditionalInfo(model.getAdditionalInfo());
        return entity;
    }
}
