package kg.founders.core.services.impl.login;

import kg.founders.core.data_access_layer.dao.LoginHistoryDao;
import kg.founders.core.entity.LogisticLoginHistory;
import kg.founders.core.services.login.LoginHistoryService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class LoginHistoryServiceImpl implements LoginHistoryService {

    LoginHistoryDao dao;
    static Integer MAX_LOGIN_ATTEMPTS = 3;

    public boolean isLoginAttemptsExceeded(String login) {
        return dao.isLoginAttemptsExceeded(login, MAX_LOGIN_ATTEMPTS);
    }

    @Transactional
    public void save(String ip, String login) {
        dao.save(
                new LogisticLoginHistory(
                        null,
                        Timestamp.valueOf(LocalDateTime.now()),
                        login,
                        ip
                )
        );
    }

    @Transactional
    public void clear(String login) {
        dao.deleteAllByLogin(login);
    }
}