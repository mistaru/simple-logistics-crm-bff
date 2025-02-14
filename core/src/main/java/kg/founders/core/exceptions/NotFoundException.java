package kg.founders.core.exceptions;

import java.util.function.Supplier;

import kg.founders.core.model.audit.IdBased;
import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {
    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

    public static Supplier<NotFoundException> fromMessage(String message) {
        return () -> {
            return new NotFoundException(message);
        };
    }

    public static Supplier<NotFoundException> fromId(IdBased id, String dictName) {
        return fromId(id.getId(), dictName);
    }

    public static Supplier<NotFoundException> fromId(Long id, String dictName) {
        return fromMessage("Запись с id " + id + " в справочнике \"" + dictName + "\" не найдена");
    }
}