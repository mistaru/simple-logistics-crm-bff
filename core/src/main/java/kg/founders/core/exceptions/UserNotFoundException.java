package kg.founders.core.exceptions;

public class UserNotFoundException extends NotFoundException {
    public static final String MESSAGE = "Пользователь с указанным именем и паролем не найден.";

    public UserNotFoundException() {
        super("Пользователь с указанным именем и паролем не найден.");
    }
}
