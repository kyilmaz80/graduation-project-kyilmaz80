package com.kyilmaz80.hotel.models;

import com.kyilmaz80.hotel.ViewUtils;
import com.kyilmaz80.hotel.utils.DBUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Map;

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

    public void selectAllCustomers() {
        ObservableList<?> newList = new DBUtils().selectEntityList("*", "Customer");
        customers = (ObservableList<Customer>) newList;
    }

    public void insertCustomer(Map<String,Object> customerInsertMap) {
        // column names must be sorted
        String sqlString = "INSERT INTO Customer (birth_date, description, full_name, identity_number, phone_number) " +
                "VALUES(?,?,?,?,?)";
        try {
            DBUtils.executeStatement(sqlString, customerInsertMap);
        } catch (RuntimeException e) {
            e.printStackTrace();
            ViewUtils.showAlert(e.getMessage());
        }
    }

    public void deleteCustomer(int id) {
        String sqlString = "DELETE FROM Customer WHERE id = ?";
        DBUtils.executeStatement(sqlString, id);
    }
}
