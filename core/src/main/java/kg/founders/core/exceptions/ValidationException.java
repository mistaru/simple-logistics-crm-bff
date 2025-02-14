package kg.founders.core.exceptions;

import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

public class ValidationException extends BadRequestException {
    private Object requestModel;

    public ValidationException(@NotNull String message) {
        super(message);
    }

    public ValidationException(Object requestModel, String message) {
        super(message);
        this.requestModel = requestModel;
    }

    public static Supplier<ValidationException> fromMessage(String message) {
        return () -> {
            return new ValidationException((Object)null, message);
        };
    }

    public Object getRequestModel() {
        return this.requestModel;
    }
}