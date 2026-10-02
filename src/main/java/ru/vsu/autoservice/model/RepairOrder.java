package ru.vsu.autoservice.model;

import ru.vsu.autoservice.exception.InvalidTransitionException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RepairOrder {

    private final long id;
    private final Client client;
    private final Car car;
    private final LocalDate acceptedDate;
    private final List<RepairWork> works;
    private OrderStatus status;

    public RepairOrder(
            long id,
            Client client,
            Car car,
            LocalDate acceptedDate,
            List<RepairWork> works
    ) {
        if (id <= 0) {
            throw new IllegalArgumentException("Идентификатор заказа должен быть положительным");
        }

        this.client = Objects.requireNonNull(client, "Клиент не может быть null");
        this.car = Objects.requireNonNull(car, "Автомобиль не может быть null");
        this.acceptedDate = Objects.requireNonNull(acceptedDate, "Дата приёма не может быть null");

        if (!car.owner().equals(client)) {
            throw new IllegalArgumentException("Автомобиль не принадлежит указанному клиенту");
        }

        Objects.requireNonNull(works, "Список работ не может быть null");

        if (works.isEmpty()) {
            throw new IllegalArgumentException("Заказ должен содержать хотя бы одну работу");
        }

        this.id = id;
        this.works = new ArrayList<>(works);
        this.status = OrderStatus.ACCEPTED;
    }

    public long id() {
        return id;
    }

    public Client client() {
        return client;
    }

    public Car car() {
        return car;
    }

    public LocalDate acceptedDate() {
        return acceptedDate;
    }

    public List<RepairWork> works() {
        return List.copyOf(works);
    }

    public OrderStatus status() {
        return status;
    }

    public long totalCostKopecks() {
        long total = 0;

        for (RepairWork work : works) {
            total += work.priceKopecks();
        }

        return total;
    }

    public void changeStatus(OrderStatus newStatus) throws InvalidTransitionException {
        Objects.requireNonNull(newStatus, "Новый статус не может быть null");

        if (!status.canTransitionTo(newStatus)) {
            throw new InvalidTransitionException(
                    "Недопустимый переход: " + status + " -> " + newStatus
            );
        }

        status = newStatus;
    }
}