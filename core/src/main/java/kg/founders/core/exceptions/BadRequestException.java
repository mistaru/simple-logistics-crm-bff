package kg.founders.core.exceptions;

public class BadRequestException extends RuntimeException {
    private Integer code;
    private Object data;

    public BadRequestException(String message, Integer code, Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }

    public BadRequestException(String message, Exception ex, Integer code, Object data) {
        super(message, ex);
        this.code = code;
        this.data = data;
    }

    public BadRequestException(String message) {
        super(message);
    }

    public Integer getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }
}
