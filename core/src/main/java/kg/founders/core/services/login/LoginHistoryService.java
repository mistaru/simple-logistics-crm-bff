package kg.founders.core.services.login;

public interface LoginHistoryService {
    boolean isLoginAttemptsExceeded(String login);

    void save(String ip, String login);

    void clear(String login);
}

