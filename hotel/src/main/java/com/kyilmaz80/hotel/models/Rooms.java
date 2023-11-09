package com.kyilmaz80.hotel.models;

public class Rooms {
    // mapping -> view_room view
    private int id;
    private int capacity;
    private double price;
    private String name;
    private int tid;
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

    public int getTid() {
        return tid;
    }
    public void setTid(int tid) {
        this.tid = tid;
    }

    public Rooms(int capacity, double price, String name, int typeId) {
        this.capacity = capacity;
        this.price = price;
        this.name = name;
        this.tid = typeId;
    }

    public Rooms(int id, int capacity, double price, String name, int typeId) {
        this.id = id;
        this.capacity = capacity;
        this.price = price;
        this.name = name;
        this.tid = typeId;
    }
}
