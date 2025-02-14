package kg.founders.core.exceptions;

import org.springframework.http.HttpStatus;

public class PasswordExpirationException extends BaseException {
    public PasswordExpirationException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}