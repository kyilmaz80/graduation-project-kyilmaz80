package com.kyilmaz80.hotel.models;

import com.kyilmaz80.hotel.utils.DBUtils;
import com.kyilmaz80.hotel.utils.JDBCUtils;
import com.kyilmaz80.hotel.utils.StringUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class RoomModel {

    private ObservableList<Room> rooms;

    private ObservableList<RoomTypes> roomTypes;

    public void setRoomTypes(ObservableList<RoomTypes> roomTypes) {
        this.roomTypes = roomTypes;
    }

    public ObservableList<Room> getRooms() {
        return rooms;
    }

    public ObservableList<RoomTypes> getRoomTypes() {
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

    public void selectRoomListFilter2(Map<String, String> columnsMap) {
        ObservableList<?> newList  = new DBUtils().selectEntityListFilter(columnsMap, "Room");
        rooms = (ObservableList<Room>) newList;
    }

    public void selectRoomListFilter(Map<String, String> columnsMap) {
        ObservableList<Room> newList  = FXCollections.observableArrayList();
        String query = "SELECT * FROM Room WHERE %s = ?";

        int i = 0;
        for (Map.Entry<String, String> entry : columnsMap.entrySet()) {
            if (i == 0) {
                query = String.format(query, StringUtils.filterStr(entry.getKey()));
            }else {
                StringBuilder sb = new StringBuilder(query);
                query = sb.append(" and %s = ?").toString();
                query = String.format(query, StringUtils.filterStr(entry.getKey()));
            }
            i++;
        }

        //String sqlString = "SELECT * FROM Room WHERE ? = ?";
        String sqlString = query;

        /*
        https://stackoverflow.com/questions/3135973/variable-column-names-using-prepared-statements
        This indicates a bad DB design. The user shouldn't need to know about the column names. Create
        a real DB column which holds those "column names" and store the data along it instead.
        And any way, no, you cannot set column names as PreparedStatement values. You can only set
        column values as PreparedStatement values
        If you'd like to continue in this direction, you need to sanitize the column names (to avoid SQL Injection)
        and concatenate/build the SQL string yourself. Quote the separate column names and use String#replace() to
        escape the same quote inside the column name.
         */

        ResultSet rs = new DBUtils().getSelectResultSetFromTable(sqlString, columnsMap);

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




    public void selectRoomTypesList() {
        ObservableList<RoomTypes> newList  = FXCollections.observableArrayList();
        String sqlString = "SELECT id, tname FROM RoomType";

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
