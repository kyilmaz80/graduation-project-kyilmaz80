package com.kyilmaz80.hotel.models;

public class RoomFeature {
    private int id;
    private int room_id;
    private int feature_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getFeature_id() {
        return feature_id;
    }

    public void setFeature_id(int feature_id) {
        this.feature_id = feature_id;
    }

    public RoomFeature(int room_id, int feature_id) {
        this.room_id = room_id;
        this.feature_id = feature_id;
    }

    public RoomFeature(int id, int room_id, int feature_id) {
        this.id = id;
        this.room_id = room_id;
        this.feature_id = feature_id;
    }

    public RoomFeature() {
        this.id = -1;
        this.room_id = -1;
        this.feature_id = -1;
    }
}
