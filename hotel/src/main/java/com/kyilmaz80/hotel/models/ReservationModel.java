package com.kyilmaz80.hotel.models;

import com.kyilmaz80.hotel.ViewUtils;
import com.kyilmaz80.hotel.utils.DBUtils;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Map;

public class ReservationModel {
    private ObservableList<Reservation> reservations;

    public ObservableList<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(ObservableList<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void selectAllReservations(String columnsStr) {
        ObservableList<?> newList = new DBUtils().selectEntityList(columnsStr, "reservation_view");
        reservations = (ObservableList<Reservation>) newList;
    }


    public void selectAllReservations() {
        ObservableList<?> newList = new DBUtils().selectEntityList("*", "reservation_view");
        reservations = (ObservableList<Reservation>) newList;
    }



    public void insertReservation(Map<String,Object> reservationInsertMap) {
        // column names must be sorted
        String sqlString = "INSERT INTO Reservation (checkin_date, checkout_date, room_id) VALUES(?,?,?)";
        // uniq constraint error'de rollback sonrasi id ler 1 artıyor, gap oluyor
        // https://dba.stackexchange.com/questions/101320/mysql-auto-increment-column-increases-after-insertion-error-occurs
        try {
            DBUtils.executeStatement(sqlString, reservationInsertMap);
        } catch (RuntimeException e) {
            e.printStackTrace();
            ViewUtils.showAlert(e.getMessage());
        }
    }


}
