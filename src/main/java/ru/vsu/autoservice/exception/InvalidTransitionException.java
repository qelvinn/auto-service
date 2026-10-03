package ru.vsu.autoservice.exception;

/**
 * Возникает при попытке выполнить недопустимый переход статуса заказа.
 */
public class InvalidTransitionException extends Exception {

    /**
     * Создаёт исключение с указанным сообщением.
     *
     * @param message описание причины ошибки
     */
    public InvalidTransitionException(String message) {
        super(message);
    }
}