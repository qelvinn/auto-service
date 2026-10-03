package ru.vsu.autoservice.model;

import ru.vsu.autoservice.exception.InvalidTransitionException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Представляет заказ клиента на обслуживание автомобиля.
 */
public class RepairOrder {

    private final long id;
    private final Client client;
    private final Car car;
    private final LocalDate acceptedDate;
    private final List<RepairWork> works;
    private OrderStatus status;

    /**
     * Создаёт заказ на ремонт.
     *
     * @param id идентификатор заказа
     * @param client клиент
     * @param car автомобиль
     * @param acceptedDate дата приёма автомобиля
     * @param works список работ
     * @throws NullPointerException если обязательный параметр равен null
     * @throws IllegalArgumentException если идентификатор неположительный,
     *                                  автомобиль принадлежит другому клиенту
     *                                  или список работ пуст
     */
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

        for (RepairWork work : works) {
            Objects.requireNonNull(work, "Работа в заказе не может быть null");
        }

        this.id = id;
        this.works = new ArrayList<>(works);
        this.status = OrderStatus.ACCEPTED;
    }

    /**
     * Возвращает идентификатор заказа.
     *
     * @return идентификатор заказа
     */
    public long id() {
        return id;
    }

    /**
     * Возвращает клиента заказа.
     *
     * @return клиент
     */
    public Client client() {
        return client;
    }

    /**
     * Возвращает автомобиль заказа.
     *
     * @return автомобиль
     */
    public Car car() {
        return car;
    }

    /**
     * Возвращает дату приёма автомобиля.
     *
     * @return дата приёма
     */
    public LocalDate acceptedDate() {
        return acceptedDate;
    }

    /**
     * Возвращает копию списка работ заказа.
     *
     * @return неизменяемый список работ
     */
    public List<RepairWork> works() {
        return List.copyOf(works);
    }

    /**
     * Возвращает текущий статус заказа.
     *
     * @return текущий статус
     */
    public OrderStatus status() {
        return status;
    }

    /**
     * Рассчитывает общую стоимость всех работ заказа.
     *
     * @return общая стоимость в копейках
     */
    public long totalCostKopecks() {
        long total = 0;

        for (RepairWork work : works) {
            total += work.priceKopecks();
        }

        return total;
    }

    /**
     * Изменяет статус заказа, если переход разрешён.
     *
     * @param newStatus новый статус
     * @throws NullPointerException если новый статус равен null
     * @throws InvalidTransitionException если переход запрещён
     */
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