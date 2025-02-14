package kg.founders.core.settings.jwt;

import java.util.Date;

public interface TokenService {
    boolean verify(String token, String username, String secret);

    String sign(String username, String secret);

    String getUsername(String token);

    Date getExpiringDate(String token);

}
