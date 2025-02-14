package kg.founders.core.exceptions;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends BaseException {
    public static final String DEFAULT_MESSAGE = "Доступ запрещен!";

    public ForbiddenException() {
        super("Доступ запрещен!", HttpStatus.FORBIDDEN);
    }

    public ForbiddenException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
