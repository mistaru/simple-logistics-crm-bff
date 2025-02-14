package kg.founders.core.services.login;

import kg.founders.core.entity.auth.LogisticAuth;
import kg.founders.core.model.login.LoginModel;

public interface LoginService {

    String login(LoginModel model, String ip);

    LogisticAuth authFromToken(String token);

    String refreshToken(String token);

}
