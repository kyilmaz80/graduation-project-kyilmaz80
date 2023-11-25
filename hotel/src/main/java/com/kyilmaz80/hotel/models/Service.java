package com.kyilmaz80.hotel.models;

public class Service {
    private int id;
    private String name;

    public Service(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Service(String name) {
        this.name = name;
    }
    public Service() {
        this.id = -1;
        this.name = "";
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

    @Override
    public String toString() {
        if (id == -1 && name.isEmpty()) {
            return "";
        } else {
            return this.getName();
        }
    }
}
