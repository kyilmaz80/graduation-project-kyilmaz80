package com.kyilmaz80.hotel.models;

import com.kyilmaz80.hotel.utils.DBUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
}
