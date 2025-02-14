package kg.founders.bff.config.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ResultCode {
    OK(200, "ОК"),
    SUCCESS(200, "Успешно"),
    FAIL(400, "Ошибка"),
    UNAUTHORIZED(401, "Пользователь не авторизован!"),
    FORBIDDEN(403, "Доступ запрещен!"),
    NOT_FOUND(404, "Не удалось найти :("),
    INTERNAL_SERVER_ERROR(500, "Что-то пошло не так :("),
    ;

    int httpCode;
    String description;
}