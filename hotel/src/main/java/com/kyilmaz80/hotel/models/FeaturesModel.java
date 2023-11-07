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

    public void selectFeatureList(String searchString) {
        ObservableList<Features> newList  = FXCollections.observableArrayList();
        String sqlString = "SELECT * FROM Feature WHERE name LIKE ? ";

        ResultSet rs = new DBUtils().getSelectResultSetFromTable(sqlString, searchString);

        if (rs == null) {
            System.out.println(JDBCUtils.error);
            return;
        }

        try {
            while(rs.next()) {
                newList.add(new Features(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            if (connection == null) {
                ViewUtils.showAlert("DB Connection problem!");
            }
            String updateString = "SELECT * FROM Feature WHERE name LIKE ? ";
            PreparedStatement ps = connection.prepareStatement(updateString);
            ps.setString(1, "%" + searchString + "%");
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                newList.add(new Features(rs.getInt("id"), rs.getString("name")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
         */

        features = newList;
    }

    public void insertFeature(String name) {
        String sqlString = "INSERT INTO Feature (name) VALUES(?)";
        DBUtils.executeStatement(sqlString, name);
    }

    public void deleteFeature(int id) {
        String sqlString = "DELETE FROM Feature WHERE id = ?";
        DBUtils.executeStatement(sqlString, id);
    }
}
