package com.kyilmaz80.hotel.models;

import com.kyilmaz80.hotel.ViewUtils;
import com.kyilmaz80.hotel.utils.DBUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Map;

public class RoomFeatureModel {
    private ObservableList<RoomFeature> roomFeatures;

    public RoomFeatureModel() {
        roomFeatures = FXCollections.observableArrayList();
    }

    public RoomFeatureModel(ObservableList<RoomFeature> roomFeatures) {
        this.roomFeatures = roomFeatures;
    }

    public ObservableList<RoomFeature> getRoomFeatures() {
        return roomFeatures;
    }

    public void setRoomFeatures(ObservableList<RoomFeature> roomFeatures) {
        this.roomFeatures = roomFeatures;
    }

    public void selectAllRoomFeatures(String columnsStr) {
        ObservableList<?> newList = new DBUtils().selectEntityList(columnsStr, "RoomFeature");
        roomFeatures = (ObservableList<RoomFeature>) newList;
    }

    public void insertRoomFeature(Map<String,String> roomFeatureInsertMap) {
        // column names must be sorted
        String sqlString = "INSERT INTO RoomFeature (feature_id, room_id) VALUES(?,?)";
        // uniq constraint error'de rollback sonrasi id ler 1 artıyor, gap oluyor
        // https://dba.stackexchange.com/questions/101320/mysql-auto-increment-column-increases-after-insertion-error-occurs
        try {
            DBUtils.executeStatement(sqlString, roomFeatureInsertMap);
        } catch (RuntimeException e) {
            e.printStackTrace();
            ViewUtils.showAlert(e.getMessage());
        }
    }

    public void deleteRoomFeature(int id) {
        String sqlString = "DELETE FROM RoomFeature WHERE id = ?";
        DBUtils.executeStatement(sqlString, id);
    }
}
