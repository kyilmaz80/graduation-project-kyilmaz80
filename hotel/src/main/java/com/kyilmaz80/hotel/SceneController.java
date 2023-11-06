package com.kyilmaz80.hotel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SceneController implements Initializable {
    @FXML
    private Button backButton;
    private Scene preScene;

    public void setPreScene(Scene preScene) {
        //System.out.println("prescene: " + backButton.getScene());
        this.preScene = preScene;
    }

    @FXML
    void onClickBack(ActionEvent event) throws IOException {
        System.out.println("On click back");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        System.out.println(preScene);
        stage.setScene(preScene);
        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image(getClass().getResourceAsStream("/images/left-arrow.png"));
        backButton.setGraphic(new ImageView(image));

    }
}
