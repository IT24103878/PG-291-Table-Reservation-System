package com.pg231.table_reservation_server.model;

import java.time.LocalDateTime;
import java.util.List;

public class Reservation extends ReservationBase {
    private List<OrderItem> items;
    private String tableId;
    private double totalPrice;
    private boolean completed;

    public Reservation() {
    }

    public Reservation(String id, String customerId, LocalDateTime dateTime, List<OrderItem> items, String tableId, double totalPrice, boolean status) {
        super(id, customerId, dateTime);
        this.items = items;
        this.tableId = tableId;
        this.totalPrice = totalPrice;
        this.completed = status;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "items=" + items +
                ", tableId='" + tableId + '\'' +
                ", totalPrice=" + totalPrice +
                ", status=" + completed +
                ", customerId='" + customerId + '\'' +
                ", dateTime=" + dateTime +
                ", id='" + id + '\'' +
                '}';
    }
}
