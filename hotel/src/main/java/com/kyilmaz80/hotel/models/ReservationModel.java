package com.kyilmaz80.hotel.models;

import com.kyilmaz80.hotel.ViewUtils;
import com.kyilmaz80.hotel.utils.DBUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.util.Map;

public class ReservationModel {
    private ObservableList<Reservation> reservations;

    public ObservableList<Reservation> getReservations() {
        return reservations;
    }

    public ReservationModel() {
        reservations = FXCollections.observableArrayList();
    }

    public void setReservations(ObservableList<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void selectAllReservations() {
        ObservableList<?> newList = new DBUtils().selectEntityList("*", "Reservation");
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

    public void deleteReservation(int id) {
        String sqlString = "DELETE FROM Reservation WHERE id = ?";
        DBUtils.executeStatement(sqlString, id);
    }

    public void updateReservationCheckedIn(int id, LocalDateTime d) {
        String sqlString = "UPDATE Reservation SET checkedin_time = ? WHERE id = ?";
        DBUtils.executeStatement(sqlString, d, id);
    }

    public void updateReservationCheckedOut(int id, LocalDateTime d) {
        String sqlString = "UPDATE Reservation SET checkedout_time = ? WHERE id = ?";
        DBUtils.executeStatement(sqlString, d, id);
    }



}
