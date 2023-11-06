package com.kyilmaz80.hotel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewUtils {

    public static void changeScene(MouseEvent event,
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


}
