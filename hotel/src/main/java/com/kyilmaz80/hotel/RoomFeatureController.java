package com.kyilmaz80.hotel;

import com.kyilmaz80.hotel.models.Features;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class RoomFeatureController extends SceneController implements Initializable {

    @FXML
    private TableView<Features> featureTableView;

    @Override
    public void setPreScene(Scene preScene) {
        super.setPreScene(preScene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
    }
}
