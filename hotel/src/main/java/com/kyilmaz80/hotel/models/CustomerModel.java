package com.kyilmaz80.hotel.models;

import com.kyilmaz80.hotel.utils.DBUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerModel {
    private ObservableList<Customer> customers;

    public CustomerModel(ObservableList<Customer> customers) {
        this.customers = customers;
    }

    public CustomerModel() {
        customers = FXCollections.observableArrayList();
    }

    public ObservableList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ObservableList<Customer> customers) {
        this.customers = customers;
    }

    public void selectAllCustomers(String columnsStr) {
        ObservableList<?> newList = new DBUtils().selectEntityList(columnsStr, "Customer");
        customers = (ObservableList<Customer>) newList;
    }
}
