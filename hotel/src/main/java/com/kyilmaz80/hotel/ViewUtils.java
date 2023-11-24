package com.kyilmaz80.hotel;

import com.kyilmaz80.hotel.controllers.SceneController;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewUtils {

    public static void changeScene(Event event,
                                   String fxmlFile,
                                   String title,
                                   int width,
                                   int height,
                                   Scene parentScene) {
        Parent root = null;
        Stage stage = null;

        try {
                FXMLLoader loader = new FXMLLoader(ViewUtils.class.getResource(fxmlFile));
                root = loader.load();
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle(title);
                stage.setScene(new Scene(root, width, height));
                System.out.println("new scene: " + title +  " prescene: " + parentScene);
                SceneController controller = loader.getController();
                controller.setPreScene(parentScene);

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (stage != null) {
            stage.show();
        }

    }

    public static void changeScene2(Event event,
                                   String fxmlFile,
                                   String title,
                                   int width,
                                   int height,
                                   Scene parentScene,
                                   Object obj) {
        Parent root = null;
        Stage stage = null;

        try {
            FXMLLoader loader = new FXMLLoader(ViewUtils.class.getResource(fxmlFile));
            root = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setUserData(obj);
            stage.setScene(new Scene(root, width, height));
            System.out.println("new scene: " + title +  " prescene: " + parentScene);
            SceneController controller = loader.getController();
            controller.setPreScene(parentScene);

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (stage != null) {
            stage.show();
        }

    }


    public static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }


}
