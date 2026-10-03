package ru.vsu.autoservice.model;

import java.util.Objects;

/**
 * Представляет автомобиль клиента.
 *
 * @param vin уникальный идентификатор автомобиля
 * @param brand марка автомобиля
 * @param model модель автомобиля
 * @param year год выпуска
 * @param owner владелец автомобиля
 */
public record Car(
        String vin,
        String brand,
        String model,
        int year,
        Client owner
) {

    /**
     * Создаёт автомобиль и проверяет корректность его данных.
     *
     * @throws NullPointerException если обязательное поле равно null
     * @throws IllegalArgumentException если строковое поле пустое
     *                                  или год выпуска некорректен
     */
    public Car {
        Objects.requireNonNull(vin, "VIN не может быть null");
        Objects.requireNonNull(brand, "Марка автомобиля не может быть null");
        Objects.requireNonNull(model, "Модель автомобиля не может быть null");
        Objects.requireNonNull(owner, "Владелец автомобиля не может быть null");

        if (vin.isBlank()) {
            throw new IllegalArgumentException("VIN не может быть пустым");
        }
        if (brand.isBlank()) {
            throw new IllegalArgumentException("Марка автомобиля не может быть пустой");
        }
        if (model.isBlank()) {
            throw new IllegalArgumentException("Модель автомобиля не может быть пустой");
        }
        if (year < 1886 || year > 2100) {
            throw new IllegalArgumentException("Некорректный год выпуска");
        }
    }

    /**
     * Сравнивает автомобили по VIN.
     *
     * @param other объект для сравнения
     * @return true, если объекты являются автомобилями с одинаковым VIN
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Car car)) {
            return false;
        }
        return vin.equals(car.vin);
    }

    /**
     * Возвращает хеш-код автомобиля на основе VIN.
     *
     * @return хеш-код VIN
     */
    @Override
    public int hashCode() {
        return Objects.hash(vin);
    }
}