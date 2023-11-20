package com.kyilmaz80.hotel.models;

import com.kyilmaz80.hotel.ViewUtils;
import com.kyilmaz80.hotel.utils.DBUtils;
import com.kyilmaz80.hotel.utils.JDBCUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

public class ReservationViewModel {
    private ObservableList<ReservationView> reservations;

    public ObservableList<ReservationView> getReservations() {
        return reservations;
    }

    public void setReservations(ObservableList<ReservationView> reservations) {
        this.reservations = reservations;
    }

    public void selectAllReservations(String columnsStr) {
        ObservableList<?> newList = new DBUtils().selectEntityList(columnsStr, "ReservationView");
        reservations = (ObservableList<ReservationView>) newList;
    }


    public void selectAllReservations() {
        ObservableList<?> newList = new DBUtils().selectEntityList("*", "ReservationView");
        reservations = (ObservableList<ReservationView>) newList;
    }

    public void selectReservationsBetweenTwo(LocalDate d1, LocalDate d2) {
        /*
        ObservableList<?> newList  = new DBUtils().selectEntityListFilter(columnsMap, "ReservationView");
        reservations = (ObservableList<ReservationView>) newList;
        //String sqlString = " SELECT * FROM ReservationView WHERE checkin_date BETWEEN ? and ?";
         */
        ObservableList<ReservationView> newList  = FXCollections.observableArrayList();
        String sqlString = "SELECT * FROM ReservationView WHERE checkin_date BETWEEN ? and ?";


        ResultSet rs = new DBUtils().getSelectResultSetFromTable(sqlString, d1, d2);

        if (rs == null) {
            System.out.println(JDBCUtils.error);
            return;
        }

        try {
            while(rs.next()) {
                newList.add(new ReservationView(
                        rs.getInt("id"),
                        rs.getInt("room_id"),
                        rs.getString("room_name"),
                        rs.getObject ("checkin_date", LocalDateTime.class),
                        rs.getObject("checkout_date", LocalDateTime.class),
                        rs.getObject("checkedin_time", LocalDateTime.class),
                        rs.getObject("checkedout_time", LocalDateTime.class),
                        rs.getString("customer_name")
                        ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        reservations = newList;
    }

    public void selectReservationListOrFilter(Map<String, Pair<String,String>> columnsMap) {
        ObservableList<?> newList  = new DBUtils().selectEntityListOrFilter(columnsMap, "ReservationView");
        reservations = (ObservableList<ReservationView>) newList;
    }

    /*
     public void selectRoomListFilter2(Map<String, Pair<String,String>> columnsMap) {
        ObservableList<?> newList  = new DBUtils().selectEntityListFilter(columnsMap, "Room");
        rooms = (ObservableList<Room>) newList;
    }
     */
}
