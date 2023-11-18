package com.kyilmaz80.hotel.models;

import com.kyilmaz80.hotel.utils.DBUtils;
import javafx.collections.ObservableList;

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


}
