package kg.founders.core.data_access_layer.dao;

import kg.founders.core.entity.LogisticLoginHistory;

public interface LoginHistoryDao {
    void save(LogisticLoginHistory logisticLoginHistory);

    boolean isLoginAttemptsExceeded(String login, Integer maxLoginAttempts);

    void deleteAllByLogin(String login);
}