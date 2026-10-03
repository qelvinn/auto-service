package ru.vsu.autoservice.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ru.vsu.autoservice.exception.InvalidTransitionException;

/**
 * Управляет заказами автосервиса и выполняет основные операции поиска.
 */
public class AutoService {

    private final List<RepairOrder> orders = new ArrayList<>();

    /**
     * Добавляет заказ в автосервис.
     *
     * @param order добавляемый заказ
     * @throws NullPointerException     если заказ равен null
     * @throws IllegalArgumentException если заказ с таким идентификатором уже существует
     */
    public void addOrder(RepairOrder order) {
        Objects.requireNonNull(order, "Заказ не может быть null");

        if (findOrderById(order.id()).isPresent()) {
            throw new IllegalArgumentException(
                    "Заказ с идентификатором " + order.id() + " уже существует"
            );
        }

        orders.add(order);
    }

    /**
     * Возвращает все заказы автосервиса.
     *
     * @return неизменяемый список заказов
     */
    public List<RepairOrder> orders() {
        return List.copyOf(orders);
    }

    /**
     * Находит заказ по идентификатору.
     *
     * @param id идентификатор заказа
     * @return найденный заказ или пустой результат
     */
    public Optional<RepairOrder> findOrderById(long id) {
        for (RepairOrder order : orders) {
            if (order.id() == id) {
                return Optional.of(order);
            }
        }

        return Optional.empty();
    }

    /**
     * Изменяет статус заказа по его идентификатору.
     *
     * @param orderId   идентификатор заказа
     * @param newStatus новый статус
     * @throws IllegalArgumentException   если заказ не найден
     * @throws NullPointerException       если новый статус равен null
     * @throws InvalidTransitionException если переход запрещён
     */
    public void changeOrderStatus(long orderId, OrderStatus newStatus)
            throws InvalidTransitionException {
        RepairOrder order = findOrderById(orderId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Заказ с идентификатором " + orderId + " не найден"
                ));

        order.changeStatus(newStatus);
    }

    /**
     * Находит автомобиль по VIN.
     *
     * @param vin VIN автомобиля
     * @return найденный автомобиль или пустой результат
     * @throws NullPointerException если VIN равен null
     */
    public Optional<Car> findCarByVin(String vin) {
        Objects.requireNonNull(vin, "VIN не может быть null");

        for (RepairOrder order : orders) {
            if (order.car().vin().equals(vin)) {
                return Optional.of(order.car());
            }
        }

        return Optional.empty();
    }

    /**
     * Возвращает заказы, находящиеся в работе на указанную дату.
     *
     * @param date дата проверки
     * @return неизменяемый список заказов в работе
     * @throws NullPointerException если дата равна null
     */
    public List<RepairOrder> getOrdersInProgress(LocalDate date) {
        Objects.requireNonNull(date, "Дата не может быть null");

        List<RepairOrder> result = new ArrayList<>();

        for (RepairOrder order : orders) {
            if (!order.acceptedDate().isAfter(date)
                    && order.status() != OrderStatus.ISSUED
                    && order.status() != OrderStatus.CANCELLED) {
                result.add(order);
            }
        }

        return List.copyOf(result);
    }
}