package kg.founders.api.services;

import kg.founders.common.models.user.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserModel> get(String search, Pageable pageable);

    UserModel create(UserModel userModel) throws Exception ;

    UserModel update(UserModel userModel);

    UserModel getCurrentUser();
}
