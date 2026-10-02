package ru.vsu.autoservice.model;

public class RepairServiceWork implements RepairWork {

    private final String description;
    private final long priceKopecks;
    private final int normHours;

    public RepairServiceWork(String description, long priceKopecks, int normHours) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Описание работы не может быть пустым");
        }
        if (priceKopecks < 0) {
            throw new IllegalArgumentException("Цена работы не может быть отрицательной");
        }
        if (normHours <= 0) {
            throw new IllegalArgumentException("Количество нормо-часов должно быть положительным");
        }

        this.description = description;
        this.priceKopecks = priceKopecks;
        this.normHours = normHours;
    }

    @Override
    public String name() {
        return description;
    }

    @Override
    public long priceKopecks() {
        return priceKopecks;
    }

    @Override
    public int normHours() {
        return normHours;
    }
}