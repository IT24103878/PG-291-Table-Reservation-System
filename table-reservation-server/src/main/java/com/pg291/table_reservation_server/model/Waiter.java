package com.pg291.table_reservation_server.model;

public class Waiter extends BaseEntity {
    private String name;
    private String nic;
    private boolean available;

    public Waiter() {
    }

    public Waiter(String id, String name, String nic, boolean available) {
        super(id);
        this.name = name;
        this.nic = nic;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Waiter{" +
                "name='" + name + '\'' +
                ", nic='" + nic + '\'' +
                ", available=" + available +
                ", id='" + id + '\'' +
                '}';
    }
}
