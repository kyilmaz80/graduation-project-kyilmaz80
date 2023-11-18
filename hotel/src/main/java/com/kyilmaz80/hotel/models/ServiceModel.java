package com.kyilmaz80.hotel.models;

import com.kyilmaz80.hotel.ViewUtils;
import com.kyilmaz80.hotel.utils.DBUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Map;

public class ServiceModel {
    private ObservableList<Service> services;

    public ServiceModel() {
        services = FXCollections.observableArrayList();
    }

    public ObservableList<Service> getServices() {
        return services;
    }

    public void setServices(ObservableList<Service> services) {
        this.services = services;
    }

    public void selectAllServices(String columnsStr) {
        ObservableList<?> newList = new DBUtils().selectEntityList(columnsStr, "Service");
        services = (ObservableList<Service>) newList;
    }

    public void insertService(Map<String,Object> serviceInsertMap) {
        // column names must be sorted
        String sqlString = "INSERT INTO Service (name) VALUES(?)";
        // uniq constraint error'de rollback sonrasi id ler 1 artıyor, gap oluyor
        // https://dba.stackexchange.com/questions/101320/mysql-auto-increment-column-increases-after-insertion-error-occurs
        try {
            DBUtils.executeStatement(sqlString, serviceInsertMap);
        } catch (RuntimeException e) {
            e.printStackTrace();
            ViewUtils.showAlert(e.getMessage());
        }
    }

    public void deleteService(int id) {
        String sqlString = "DELETE FROM Service WHERE id = ?";
        DBUtils.executeStatement(sqlString, id);
    }
}
