package com.kyilmaz80.hotel.models;

public class RoomType {
    private final int roomTypeId;
    private final String roomTypeName;

    public RoomType(int roomTypeId, String roomTypeName) {
        this.roomTypeId = roomTypeId;
        this.roomTypeName = roomTypeName;
    }

    public RoomType() {
        // add empty room type
        this.roomTypeId = -1;
        this.roomTypeName = "";
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    @Override
    public String toString() {
        if (roomTypeId == -1 && roomTypeName.isEmpty()) {
            return "";
        } else {
            return this.getRoomTypeName();
        }
    }

    public int getId() {
        return roomTypeId;
    }


}
