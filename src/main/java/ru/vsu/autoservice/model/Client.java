package ru.vsu.autoservice.model;

import java.util.Objects;

public record Client(long id, String name, String phone) {

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