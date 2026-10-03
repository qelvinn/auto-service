package ru.vsu.autoservice.model;

import java.util.Objects;

/**
 * Представляет клиента автосервиса.
 *
 * @param id уникальный положительный идентификатор клиента
 * @param name имя клиента
 * @param phone номер телефона клиента
 */
public record Client(long id, String name, String phone) {

    /**
     * Создаёт клиента и проверяет корректность его данных.
     *
     * @throws IllegalArgumentException если идентификатор неположительный
     *                                  или имя либо телефон пустые
     * @throws NullPointerException если имя или телефон равны null
     */
    public Client {
        if (id <= 0) {
            throw new IllegalArgumentException("Идентификатор клиента должен быть положительным");
        }
        Objects.requireNonNull(name, "Имя клиента не может быть null");
        Objects.requireNonNull(phone, "Телефон клиента не может быть null");

        if (name.isBlank()) {
            throw new IllegalArgumentException("Имя клиента не может быть пустым");
        }
        if (phone.isBlank()) {
            throw new IllegalArgumentException("Телефон клиента не может быть пустым");
        }
    }
}