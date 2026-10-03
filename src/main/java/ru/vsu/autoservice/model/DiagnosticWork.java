package ru.vsu.autoservice.model;

/**
 * Представляет диагностическую работу.
 */
public class DiagnosticWork implements RepairWork {

    private final long priceKopecks;
    private final int normHours;

    /**
     * Создаёт диагностическую работу.
     *
     * @param priceKopecks стоимость в копейках
     * @param normHours количество нормо-часов
     * @throws IllegalArgumentException если стоимость отрицательная
     *                                  или количество нормо-часов неположительное
     */
    public DiagnosticWork(long priceKopecks, int normHours) {
        if (priceKopecks < 0) {
            throw new IllegalArgumentException("Цена работы не может быть отрицательной");
        }
        if (normHours <= 0) {
            throw new IllegalArgumentException("Количество нормо-часов должно быть положительным");
        }

        this.priceKopecks = priceKopecks;
        this.normHours = normHours;
    }

    /**
     * Возвращает название диагностической работы.
     *
     * @return название работы
     */
    @Override
    public String name() {
        return "Диагностика";
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