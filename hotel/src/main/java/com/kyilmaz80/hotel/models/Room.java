package com.kyilmaz80.hotel.models;

import java.math.BigDecimal;

public class Room {
    private int id;
    private int capacity;
    private BigDecimal price;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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

    public Room(int capacity, BigDecimal price, String name, int typeId) {
        this.capacity = capacity;
        this.price = price;
        this.name = name;
        this.tid = typeId;
    }

    public Room(int id, int capacity, BigDecimal price, String name, int typeId) {
        this.id = id;
        this.capacity = capacity;
        this.price = price;
        this.name = name;
        this.tid = typeId;
    }

    public Room() {
        this.id = 0;
        this.capacity = 0;
        this.price = BigDecimal.ZERO;
        this.name = "";
        this.tid = 0;
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
