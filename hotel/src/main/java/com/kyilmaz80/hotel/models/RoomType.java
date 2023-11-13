package com.kyilmaz80.hotel.models;

public class RoomType {
    private int id;


    private String tname;

    public RoomType(int id, String tname) {
        this.id = id;
        this.tname = tname;
    }

    public RoomType() {
        // add empty room type
        this.id = -1;
        this.tname = "";
    }


    public String getTname() {
        return tname;
    }
    public void setTname(String tname) {
        this.tname = tname;
    }


    @Override
    public String toString() {
        if (id == -1 && tname.isEmpty()) {
            return "";
        } else {
            return this.getTname();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
