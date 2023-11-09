package com.kyilmaz80.hotel.models;

import com.kyilmaz80.hotel.utils.DBUtils;
import com.kyilmaz80.hotel.utils.JDBCUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class FeaturesModel {
    private ObservableList<Features> features;

    public ObservableList<Features> getFeatures() {
        return features;
    }

    public void setFeatures(ObservableList<Features> features) {
        this.features = features;
    }

    public FeaturesModel() {
        features = FXCollections.observableArrayList();
        //features.add(new Features("TV"));
    }

    public void selectAllFeatures() {
        selectFeatureListLike("");
    }

    public void selectFeatureListLike(String searchString) {
        ObservableList<Features> newList  = FXCollections.observableArrayList();
        String sqlString = "SELECT * FROM Feature WHERE name LIKE ?";

        ResultSet rs = new DBUtils().getSelectResultSetFromTable(sqlString, searchString);

        if (rs == null) {
            System.out.println(JDBCUtils.error);
            return;
        }

        try {
            while(rs.next()) {
                newList.add(new Features(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        features = newList;
    }

    public void selectFeatureListLikeFilter(String searchString, String filterPrice) {
        ObservableList<Features> newList  = FXCollections.observableArrayList();
        String sqlString = "SELECT * FROM Feature WHERE name LIKE ? and price = ? ";
        // SELECT * FROM upod_otel.Feature WHERE name like "%man%" and price = price;
        // SELECT * FROM upod_otel.Feature WHERE name like "%man%";


        ResultSet rs = new DBUtils().getSelectResultSetFromTable(sqlString, searchString, filterPrice);

        if (rs == null) {
            System.out.println(JDBCUtils.error);
            return;
        }

        try {
            while(rs.next()) {
                newList.add(new Features(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        features = newList;
    }

    public void insertFeature(String name) {
        String sqlString = "INSERT INTO Feature (name) VALUES(?)";
        DBUtils.executeStatement(sqlString, name);
    }
    public void insertFeature(String name, Double price) {
        String sqlString = "INSERT INTO Feature (name,price) VALUES(?,?)";
        DBUtils.executeStatement(sqlString, name, price);
    }

    public void deleteFeature(int id) {
        String sqlString = "DELETE FROM Feature WHERE id = ?";
        DBUtils.executeStatement(sqlString, id);
    }
}
