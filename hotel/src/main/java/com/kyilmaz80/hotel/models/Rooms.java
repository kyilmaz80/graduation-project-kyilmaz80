package com.kyilmaz80.hotel.models;

public class Rooms {
    private int id;
    private int capacity;
    private double price;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rooms(int capacity, double price, String name) {
        this.capacity = capacity;
        this.price = price;
        this.name = name;
    }

    public Rooms(int id, int capacity, double price, String name) {
        this.id = id;
        this.capacity = capacity;
        this.price = price;
        this.name = name;
    }
}
