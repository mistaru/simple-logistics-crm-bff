package kg.founders.core.util;

import kg.founders.core.exceptions.ValidationException;
import kg.founders.core.model.audit.IdBased;

public interface Validatable {
    String validateMessage();

    default void validate() throws ValidationException {
        String r = this.validateMessage();
        if (r != null) {
            throw new ValidationException(this, r);
        }
    }

    static String validateId(IdBased id, String message) {
        return id == null ? message : validateId(id.getId());
    }

    static String validateId(Long id) {
        return id != null && id >= 1L ? null : "Не верный диапазон id";
    }
}