package com.kyilmaz80.hotel.models;

public class RoomTypes {
    private int roomTypeId;
    private String roomTypeName;

    public RoomTypes(int roomTypeId, String roomTypeName) {
        this.roomTypeId = roomTypeId;
        this.roomTypeName = roomTypeName;
    }

    public RoomTypes() {
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
