package kg.founders.core.data_access_layer.dao.impl;

import kg.founders.core.data_access_layer.dao.LoginHistoryDao;
import kg.founders.core.entity.LogisticLoginHistory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoginHistoryDaoImpl implements LoginHistoryDao {
    JdbcTemplate jdbc;

    @Override
    public void save(LogisticLoginHistory logisticLoginHistory) {
        jdbc.update("insert into " + LogisticLoginHistory.TABLE_NAME + " (id, cdt, ip, login)" +
                        " values (NEXTVAL('logistic_login_history_seq'), ?, ?, ?);"
                , logisticLoginHistory.getCdt(), logisticLoginHistory.getIp(), logisticLoginHistory.getLogin());
    }

    @Override
    public boolean isLoginAttemptsExceeded(String login, Integer maxLoginAttempts) {
        var result = jdbc.queryForObject("SELECT" +
                "    CASE  " +
                "        WHEN COUNT(*) > ? THEN 1 " +
                "        ELSE 0 " +
                "    END AS exceeded " +
                " FROM " + LogisticLoginHistory.TABLE_NAME +
                " WHERE login = ? ", Boolean.class, maxLoginAttempts, login);
        return result != null && result;
    }

    @Override
    public void deleteAllByLogin(String login) {
        jdbc.update("delete from " + LogisticLoginHistory.TABLE_NAME + " where login = ?", login);
    }
}
