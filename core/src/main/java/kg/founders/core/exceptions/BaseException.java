package kg.founders.core.exceptions;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {
    private final HttpStatus status;

    public BaseException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public BaseException(BaseException e) {
        super(e.getMessage(), e);
        this.status = e.getStatus();
    }

    public HttpStatus getStatus() {
        return this.status;
    }
}