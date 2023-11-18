package com.kyilmaz80.hotel.models;

import java.sql.Timestamp;

public class Reservation {
    //reservation_view mapped
    private int id;
    private int room_id;
    private Timestamp checkin_date;
    private Timestamp checkout_date;
    private Timestamp checkedin_date;
    private Timestamp checkedout_date;
    private String customer_name;

    public Reservation(int id, int room_id, Timestamp checkin_date, Timestamp checkout_date,
                       Timestamp checkedin_date, Timestamp checkedout_date, String customer_name) {
        this.id = id;
        this.room_id = room_id;
        this.checkin_date = checkin_date;
        this.checkout_date = checkout_date;
        this.checkedin_date = checkedin_date;
        this.checkedout_date = checkedout_date;
        this.customer_name = customer_name;
    }

    public Reservation() {
        this.id = -1;
        this.room_id = 0;
        this.checkin_date = null;
        this.checkout_date = null;
        this.checkedin_date = null;
        this.checkedout_date = null;
        this.customer_name = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public Timestamp getCheckin_date() {
        return checkin_date;
    }

    public void setCheckin_date(Timestamp checkin_date) {
        this.checkin_date = checkin_date;
    }

    public Timestamp getCheckout_date() {
        return checkout_date;
    }

    public void setCheckout_date(Timestamp checkout_date) {
        this.checkout_date = checkout_date;
    }

    public Timestamp getCheckedin_date() {
        return checkedin_date;
    }

    public void setCheckedin_date(Timestamp checkedin_date) {
        this.checkedin_date = checkedin_date;
    }

    public Timestamp getCheckedout_date() {
        return checkedout_date;
    }

    public void setCheckedout_date(Timestamp checkedout_date) {
        this.checkedout_date = checkedout_date;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
}
