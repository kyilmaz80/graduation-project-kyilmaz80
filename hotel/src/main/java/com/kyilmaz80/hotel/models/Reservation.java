package com.kyilmaz80.hotel.models;

import java.time.LocalDateTime;

public class Reservation {
    private int id;
    private int room_id;
    private LocalDateTime checkin_date;
    private LocalDateTime checkout_date;
    private LocalDateTime checkedin_time;
    private LocalDateTime checkedout_time;

    public Reservation(int id, int room_id, LocalDateTime checkin_date, LocalDateTime checkout_date,
                           LocalDateTime checkedin_time, LocalDateTime checkedout_time) {
        this.id = id;
        this.room_id = room_id;
        this.checkin_date = checkin_date;
        this.checkout_date = checkout_date;
        this.checkedin_time = checkedin_time;
        this.checkedout_time = checkedout_time;
    }

    public Reservation() {
        this.id = -1;
        this.room_id = 0;
        this.checkin_date = null;
        this.checkout_date = null;
        this.checkedin_time = null;
        this.checkedout_time = null;
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

    public LocalDateTime getCheckin_date() {
        return checkin_date;
    }

    public void setCheckin_date(LocalDateTime checkin_date) {
        this.checkin_date = checkin_date;
    }

    public LocalDateTime getCheckout_date() {
        return checkout_date;
    }

    public void setCheckout_date(LocalDateTime checkout_date) {
        this.checkout_date = checkout_date;
    }

    public LocalDateTime getCheckedin_time() {
        return checkedin_time;
    }

    public void setCheckedin_time(LocalDateTime checkedin_time) {
        this.checkedin_time = checkedin_time;
    }

    public LocalDateTime getCheckedout_time() {
        return checkedout_time;
    }

    public void setCheckedout_time(LocalDateTime checkedout_time) {
        this.checkedout_time = checkedout_time;
    }

}
