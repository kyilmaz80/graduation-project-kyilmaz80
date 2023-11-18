package com.kyilmaz80.hotel.models;

import com.kyilmaz80.hotel.ViewUtils;
import com.kyilmaz80.hotel.utils.DBUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Map;

public class ReservationCustomerModel {
    private ObservableList<ReservationCustomer> reservationCustomers;

    public ReservationCustomerModel() {
        reservationCustomers = FXCollections.observableArrayList();
    }

    public ObservableList<ReservationCustomer> getReservationCustomers() {
        return reservationCustomers;
    }

    public void selectAllReservationCustomers() {
        ObservableList<?> newList = new DBUtils().selectEntityList("*", "ReservationCustomer");
        reservationCustomers = (ObservableList<ReservationCustomer>) newList;
    }

    public void setReservationCustomers(ObservableList<ReservationCustomer> reservationCustomers) {
        this.reservationCustomers = reservationCustomers;
    }

    public void insertReservationCustomer(Map<String,Object> reservationCustomerInsertMap) {
        // column names must be sorted
        String sqlString = "INSERT INTO ReservationCustomer (customer_id, reservation_id) VALUES(?,?)";
        // uniq constraint error'de rollback sonrasi id ler 1 artıyor, gap oluyor
        // https://dba.stackexchange.com/questions/101320/mysql-auto-increment-column-increases-after-insertion-error-occurs
        try {
            DBUtils.executeStatement(sqlString, reservationCustomerInsertMap);
        } catch (RuntimeException e) {
            e.printStackTrace();
            ViewUtils.showAlert(e.getMessage());
        }
    }
}
