package com.kyilmaz80.hotel.models;

import java.math.BigDecimal;

public class ReservationService {
    private int id;
    private int reservation_id;
    private int service_id;
    private BigDecimal unit_price;
    private int quantity;

    public ReservationService(int id, int reservation_id, int service_id, BigDecimal unit_price, int quantity) {
        this.id = id;
        this.reservation_id = reservation_id;
        this.service_id = service_id;
        this.unit_price = unit_price;
        this.quantity = quantity;
    }

    public ReservationService() {
        this.id = -1;
        this.reservation_id = 0;
        this.service_id = 0;
        this.unit_price = BigDecimal.ZERO;
        this.quantity = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
