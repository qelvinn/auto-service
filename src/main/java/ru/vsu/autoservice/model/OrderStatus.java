package ru.vsu.autoservice.model;

/**
 * Содержит возможные статусы заказа на ремонт.
 */
public enum OrderStatus {
    ACCEPTED,
    DIAGNOSTICS,
    APPROVAL,
    REPAIR,
    READY,
    ISSUED,
    CANCELLED;

    /**
     * Проверяет, разрешён ли переход из текущего статуса в указанный.
     *
     * @param nextStatus следующий статус
     * @return true, если переход разрешён
     */
    public boolean canTransitionTo(OrderStatus nextStatus) {
        if (nextStatus == null || this == nextStatus) {
            return false;
        }

        return switch (this) {
            case ACCEPTED -> nextStatus == DIAGNOSTICS || nextStatus == CANCELLED;
            case DIAGNOSTICS -> nextStatus == APPROVAL || nextStatus == CANCELLED;
            case APPROVAL -> nextStatus == REPAIR || nextStatus == CANCELLED;
            case REPAIR -> nextStatus == READY || nextStatus == CANCELLED;
            case READY -> nextStatus == ISSUED;
            case ISSUED, CANCELLED -> false;
        };
    }
}