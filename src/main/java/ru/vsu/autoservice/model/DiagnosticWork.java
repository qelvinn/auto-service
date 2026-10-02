package ru.vsu.autoservice.model;

public class DiagnosticWork implements RepairWork {

    private final long priceKopecks;
    private final int normHours;

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

    @Override
    public String name() {
        return "Диагностика";
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