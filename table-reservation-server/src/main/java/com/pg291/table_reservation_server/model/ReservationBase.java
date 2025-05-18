package com.pg291.table_reservation_server.model;

import java.time.LocalDateTime;

public class ReservationBase extends BaseEntity {
    protected String customerId;
    protected LocalDateTime dateTime;

    public ReservationBase() {
    }

    public ReservationBase(String id, String customerId, LocalDateTime dateTime) {
        super(id);
        this.customerId = customerId;
        this.dateTime = dateTime;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "customerId='" + customerId + '\'' +
                ", dateTime=" + dateTime +
                ", id='" + id + '\'' +
                '}';
    }
}
