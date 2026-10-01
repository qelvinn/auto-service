package ru.vsu.autoservice.model;

import java.util.Objects;


public record Car(
        String vin,
        String brand,
        String model,
        int year,
        Client owner
) {

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


    @Override
    public int hashCode() {
        return Objects.hash(vin);
    }
}