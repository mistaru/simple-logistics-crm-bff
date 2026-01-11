package kg.founders.core.services;

import kg.founders.core.model.ClientProfileModel;

import java.util.List;

public interface ClientProfileService {
    List<ClientProfileModel> getAll();

    List<ClientProfileModel> findALlByManagerId(Long currentUserId);
}
