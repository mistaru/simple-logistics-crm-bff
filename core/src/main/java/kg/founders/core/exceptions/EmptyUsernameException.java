package kg.founders.core.exceptions;

public class EmptyUsernameException extends BadRequestException {
    public static final String MESSAGE = "Имя пользователя должно быть задано";

    public EmptyUsernameException() {
        super("Имя пользователя должно быть задано");
    }
}
