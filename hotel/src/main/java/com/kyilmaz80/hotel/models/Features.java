package com.kyilmaz80.hotel.models;

public class Features {
    private int id;
    private String name;
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Features(String name) {
        this.name = name;
        this.price = 0;
    }

    public Features(int id, String name) {
        this.id = id;
        this.name = name;
        this.price = 0;
    }

    public Features(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
