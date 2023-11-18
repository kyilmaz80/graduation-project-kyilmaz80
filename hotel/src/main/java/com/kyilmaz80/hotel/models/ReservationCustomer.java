package com.kyilmaz80.hotel.models;

public class ReservationCustomer {
    private Integer id;
    private Integer reservation_id;
    private Integer customer_id;

    public ReservationCustomer(Integer id, Integer reservation_id, Integer customer_id) {
        this.id = id;
        this.reservation_id = reservation_id;
        this.customer_id = customer_id;
    }

    public ReservationCustomer() {
        this.id = -1;
        this.customer_id = -1;
        this.reservation_id = -1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(Integer reservation_id) {
        this.reservation_id = reservation_id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }
}
