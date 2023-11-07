package com.kyilmaz80.hotel.models;

public class Features {
    private int id;
    private String name;

    public Features(String name) {
        this.name = name;
    }

    public Features(int id, String name) {
        this.id = id;
        this.name = name;
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
