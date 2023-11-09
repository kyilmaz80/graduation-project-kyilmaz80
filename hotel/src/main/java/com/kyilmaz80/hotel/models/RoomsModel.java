package com.kyilmaz80.hotel.models;

import com.kyilmaz80.hotel.utils.DBUtils;
import com.kyilmaz80.hotel.utils.JDBCUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomsModel {

    private ObservableList<Rooms> rooms;

    private ObservableList<RoomTypes> roomTypes;

    public void setRoomTypes(ObservableList<RoomTypes> roomTypes) {
        this.roomTypes = roomTypes;
    }

    public ObservableList<Rooms> getRooms() {
        return rooms;
    }

    public ObservableList<RoomTypes> getRoomTypes() {
        return roomTypes;
    }

    public RoomsModel() {
        rooms = FXCollections.observableArrayList();
        roomTypes =  FXCollections.observableArrayList();
    }


    public void setRooms(ObservableList<Rooms> rooms) {
        this.rooms = rooms;
    }

    public void selectAllRooms() {
        selectRoomsListLike("");
    }


    public void selectRoomsListLike(String searchString) {
        ObservableList<Rooms> newList  = FXCollections.observableArrayList();
        String sqlString = "SELECT * FROM Room WHERE name LIKE ?";

        ResultSet rs = new DBUtils().getSelectResultSetFromTable(sqlString, searchString);

        if (rs == null) {
            System.out.println(JDBCUtils.error);
            return;
        }

        try {
            while(rs.next()) {
                newList.add(new Rooms(rs.getInt("id"),
                        rs.getInt("capacity"),
                        rs.getDouble("price"),
                        rs.getString("name"),
                        rs.getInt("room_type_id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        rooms = newList;
    }

    public void selectRoomTypesList() {
        ObservableList<RoomTypes> newList  = FXCollections.observableArrayList();
        String sqlString = "SELECT * FROM RoomType";

        ResultSet rs = new DBUtils().getSelectResultSetFromTable(sqlString);

        if (rs == null) {
            System.out.println(JDBCUtils.error);
            return;
        }

        try {
            while(rs.next()) {
                newList.add(new RoomTypes(rs.getInt("id"),
                        rs.getString("tname")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        roomTypes = newList;

    }

}
