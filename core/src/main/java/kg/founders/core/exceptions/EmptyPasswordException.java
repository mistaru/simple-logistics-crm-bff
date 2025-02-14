package kg.founders.core.exceptions;

public class EmptyPasswordException extends BadRequestException {
    public static final String MESSAGE = "Пароль должен быть задан";

    public EmptyPasswordException() {
        super("Пароль должен быть задан");
    }
}