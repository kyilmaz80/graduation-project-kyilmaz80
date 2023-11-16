package com.kyilmaz80.hotel.models;

import java.sql.Date;

public class Customer {
    private int id;
    private String full_name;
    private String identity_number;
    private String phone_number;
    private Date birth_date;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getIdentity_number() {
        return identity_number;
    }

    public void setIdentity_number(String identity_number) {
        this.identity_number = identity_number;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Customer(int id, String full_name, String identity_number, String phone_number,
                    Date birth_date, String description) {
        this.id = id;
        this.full_name = full_name;
        this.identity_number = identity_number;
        this.phone_number = phone_number;
        this.birth_date = birth_date;
        this.description = description;
    }

    public Customer(String full_name, String identity_number) {
        this.full_name = full_name;
        this.identity_number = identity_number;
    }

    public Customer() {
        this.id = 0;
        this.full_name = "Zero";
        this.identity_number = "11111111111";
        this.phone_number = "1111111111";
        this.birth_date = Date.valueOf("1970-01-01");
        this.description = "Test";
    }
}
