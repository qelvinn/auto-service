package ru.vsu.autoservice.model;

public enum OrderStatus {
    ACCEPTED,
    DIAGNOSTICS,
    APPROVAL,
    REPAIR,
    READY,
    ISSUED,
    CANCELLED;

    public boolean canTransitionTo(OrderStatus nextStatus) {
        if (nextStatus == null) {
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