package com.kyilmaz80.hotel.models;

import com.kyilmaz80.hotel.ViewUtils;
import com.kyilmaz80.hotel.utils.DBUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Map;

public class ReservationServiceModel {

    private ObservableList<ReservationService> reservationServices;


    public ReservationServiceModel() {
        reservationServices = FXCollections.observableArrayList();
    }

    public ObservableList<ReservationService> getReservationServices() {
        return reservationServices;
    }

    public void setReservationServices(ObservableList<ReservationService> reservationServices) {
        this.reservationServices = reservationServices;
    }

    public void selectAllReservationServices() {
        ObservableList<?> newList = new DBUtils().selectEntityList("*", "ReservationService");
        reservationServices = (ObservableList<ReservationService>) newList;
    }

    public void selectAllReservationServices(String columnStr) {
        ObservableList<?> newList = new DBUtils().selectEntityList(columnStr, "ReservationService");
        reservationServices = (ObservableList<ReservationService>) newList;
    }

    public void insertReservationService(Map<String,Object> reservationServiceInsertMap) {
        // column names must be sorted
        String sqlString = "INSERT INTO ReservationService (quantity, reservation_id, service_id, unit_price) VALUES(?,?,?,?)";
        // uniq constraint error'de rollback sonrasi id ler 1 artıyor, gap oluyor
        // https://dba.stackexchange.com/questions/101320/mysql-auto-increment-column-increases-after-insertion-error-occurs
        try {
            DBUtils.executeStatement(sqlString, reservationServiceInsertMap);
        } catch (RuntimeException e) {
            e.printStackTrace();
            ViewUtils.showAlert(e.getMessage());
        }
    }

    public void deleteReservationService(int id) {
        String sqlString = "DELETE FROM ReservationService WHERE id = ?";
        DBUtils.executeStatement(sqlString, id);
    }

    public void deleteReservationServiceByReservationId(int id) {
        String sqlString = "DELETE FROM ReservationService WHERE reservation_id = ?";
        DBUtils.executeStatement(sqlString, id);
    }
}
