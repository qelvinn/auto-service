package ru.vsu.autoservice.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AutoService {

    private final List<RepairOrder> orders = new ArrayList<>();

    public void addOrder(RepairOrder order) {
        orders.add(Objects.requireNonNull(order, "Заказ не может быть null"));
    }

    public List<RepairOrder> orders() {
        return List.copyOf(orders);
    }

    public Optional<Car> findCarByVin(String vin) {
        Objects.requireNonNull(vin, "VIN не может быть null");

        for (RepairOrder order : orders) {
            if (order.car().vin().equals(vin)) {
                return Optional.of(order.car());
            }
        }

        return Optional.empty();
    }

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