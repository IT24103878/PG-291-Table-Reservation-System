package com.pg291.table_reservation_server.model;

import java.time.LocalDateTime;

public class ReservationRequest extends ReservationBase {
    private int count;

    public ReservationRequest() {
    }

    public ReservationRequest(String id, String customerId, LocalDateTime dateTime, int count) {
        super(id, customerId, dateTime);
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ReservationRequest{" +
                "count=" + count +
                ", customerId='" + customerId + '\'' +
                ", dateTime=" + dateTime +
                ", id='" + id + '\'' +
                '}';
    }
}
