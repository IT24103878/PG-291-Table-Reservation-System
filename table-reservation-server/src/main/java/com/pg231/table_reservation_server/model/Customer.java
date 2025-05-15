package com.pg231.table_reservation_server.model;

public class Customer extends BaseEntity {
    private String name;
    private String phone;

    public Customer() {
    }

    public Customer(String id, String name, String phone) {
        super(id);
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
