package kg.founders.core.exceptions;

import org.springframework.http.HttpStatus;

public class AuthenticationException extends BaseException {
    public static final String MESSAGE = "Token is not valid";

    public AuthenticationException() {
        super("Token is not valid", HttpStatus.UNAUTHORIZED);
    }

    public AuthenticationException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
