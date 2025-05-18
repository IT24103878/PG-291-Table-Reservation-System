package com.pg291.table_reservation_server.model;

public class OrderItem {
    private String name;
    private int quantity;
    private double price;

    public OrderItem() {
    }

    public OrderItem(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name + "," + quantity + "," + price;
    }

    public static OrderItem fromString(String str) {
        String[] parts = str.split(",");
        return new OrderItem(parts[0], Integer.parseInt(parts[1]), Double.parseDouble(parts[2]));
    }
}
