package com.kyilmaz80.hotel.models;

import com.kyilmaz80.hotel.ViewUtils;
import com.kyilmaz80.hotel.utils.DBUtils;
import com.kyilmaz80.hotel.utils.JDBCUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class RoomModel {

    private ObservableList<Room> rooms;

    private ObservableList<RoomType> roomTypes;

    public void setRoomTypes(ObservableList<RoomType> roomTypes) {
        this.roomTypes = roomTypes;
    }

    public ObservableList<Room> getRooms() {
        return rooms;
    }

    public ObservableList<RoomType> getRoomTypes() {
        return roomTypes;
    }

    public RoomModel() {
        rooms = FXCollections.observableArrayList();
        roomTypes =  FXCollections.observableArrayList();
    }


    public void setRooms(ObservableList<Room> rooms) {
        this.rooms = rooms;
    }

    public void selectAllRooms() {
        selectRoomListLike("");
    }


    public void selectRoomListLike(String searchString) {
        ObservableList<Room> newList  = FXCollections.observableArrayList();
        String sqlString = "SELECT * FROM Room WHERE name LIKE ?";

        ResultSet rs = new DBUtils().getSelectResultSetFromTable(sqlString, searchString);

        if (rs == null) {
            System.out.println(JDBCUtils.error);
            return;
        }

        try {
            while(rs.next()) {
                newList.add(new Room(rs.getInt("id"),
                        rs.getInt("capacity"),
                        rs.getBigDecimal("price"),
                        rs.getString("name"),
                        rs.getInt("tid")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        rooms = newList;
    }

    @Deprecated
    public void selectRoomListFilter(String searchCol, String searchString) {
        ObservableList<Room> newList  = FXCollections.observableArrayList();
        String sqlString = "SELECT * FROM Room WHERE ? = ?";


        ResultSet rs = new DBUtils().getSelectResultSetFromTable(sqlString, searchCol, searchString);

        if (rs == null) {
            System.out.println(JDBCUtils.error);
            return;
        }

        try {
            while(rs.next()) {
                newList.add(new Room(rs.getInt("id"),
                        rs.getInt("capacity"),
                        rs.getBigDecimal("price"),
                        rs.getString("name"),
                        rs.getInt("tid")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        rooms = newList;
    }

    public void selectRoomListFilter2(Map<String, Pair<String,String>> columnsMap) {
        ObservableList<?> newList  = new DBUtils().selectEntityListFilter(columnsMap, "Room");
        rooms = (ObservableList<Room>) newList;
    }


    @Deprecated
    public void selectRoomTypesList() {
        ObservableList<RoomType> newList  = FXCollections.observableArrayList();
        String sqlString = "SELECT id, tname FROM RoomType";

        ResultSet rs = new DBUtils().getSelectResultSetFromTable(sqlString);

        if (rs == null) {
            System.out.println(JDBCUtils.error);
            return;
        }

        try {
            while(rs.next()) {
                newList.add(new RoomType(rs.getInt("id"),
                        rs.getString("tname")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        roomTypes = newList;

    }

    public void selectRoomTypesList2(String columnsStr) {
        //ObservableList<?> newList  = new DBUtils().selectEntityListFilter(columnsMap, "RoomType");
        ObservableList<?> newList = new DBUtils().selectEntityList(columnsStr, "RoomType");
        roomTypes = (ObservableList<RoomType>) newList;
    }

    public void insertRoom(Map<String,String> roomInsertMap) {
        String sqlString = "INSERT INTO Room (capacity, name, price, tid) VALUES(?,?,?,?)";
        try {
            DBUtils.executeStatement(sqlString, roomInsertMap);
        } catch (RuntimeException e) {
            e.printStackTrace();
            ViewUtils.showAlert(e.getMessage());
        }
    }

    public void deleteRoom(int id) {
        String sqlString = "DELETE FROM Room WHERE id = ?";
        DBUtils.executeStatement(sqlString, id);
    }

}
