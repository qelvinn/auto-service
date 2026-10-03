package ru.vsu.autoservice.model;

import java.util.Objects;

/**
 * Представляет обычную ремонтную работу.
 */
public class RepairServiceWork implements RepairWork {

    private final String description;
    private final long priceKopecks;
    private final int normHours;

    /**
     * Создаёт ремонтную работу.
     *
     * @param description описание работы
     * @param priceKopecks стоимость в копейках
     * @param normHours количество нормо-часов
     * @throws NullPointerException если описание равно null
     * @throws IllegalArgumentException если описание пустое,
     *                                  стоимость отрицательная
     *                                  или количество нормо-часов неположительное
     */
    public RepairServiceWork(String description, long priceKopecks, int normHours) {
        Objects.requireNonNull(description, "Описание работы не может быть null");

        if (description.isBlank()) {
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

    /**
     * Возвращает описание работы.
     *
     * @return описание работы
     */
    @Override
    public String name() {
        return description;
    }

    /**
     * Возвращает стоимость работы в копейках.
     *
     * @return стоимость в копейках
     */
    @Override
    public long priceKopecks() {
        return priceKopecks;
    }

    /**
     * Возвращает нормативную продолжительность работы.
     *
     * @return количество нормо-часов
     */
    @Override
    public int normHours() {
        return normHours;
    }
}