package com.pg231.table_reservation_server.model;

public class Table extends BaseEntity {
    private int number;
    private int capacity;
    private boolean available;
    private String waiterId;

    public Table() {
    }

    public Table(String id, int number, int capacity, boolean available, String waiterId) {
        super(id);
        this.number = number;
        this.capacity = capacity;
        this.available = available;
        this.waiterId = waiterId;
    }

    public int getNumber() {
        return number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(String waiterId) {
        this.waiterId = waiterId;
    }

    @Override
    public String toString() {
        return "Table{" +
                "number=" + number +
                ", capacity=" + capacity +
                ", available=" + available +
                ", waiterId='" + waiterId + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
