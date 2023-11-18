package com.kyilmaz80.hotel.controllers;

import com.kyilmaz80.hotel.DomainConstants;
import com.kyilmaz80.hotel.ViewUtils;
import com.kyilmaz80.hotel.utils.StringUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;

public class HotelController extends SceneController implements Initializable {
    @FXML
    protected Label roomsLabel;

    @FXML
    private Label featuresLabel;

    @FXML
    private Label servicesLabel;

    @FXML
    private Label customersLabel;

    @FXML
    private Button reservationButton;

    /*
    @FXML
    private void openScene(MouseEvent event) {
        String viewName = selectedLabel.toLowerCase().replaceAll("label", "");
        String viewFile = viewName + "-view.fxml";
        String titleName = StringUtils.toUpperFirstChar(viewName);
        System.out.println("view file: " + viewFile);
        Scene currentScene = roomsLabel.getScene();
        System.out.println("current scene: " + currentScene);
        ViewUtils.changeScene(event,
                viewFile,
                titleName,
                DomainConstants.HOTEL_APP_WINDOW_WIDTH,
                DomainConstants.HOTEL_APP_WINDOW_HEIGHT,
                currentScene);
    }
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
        reservationButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Scene currentScene = reservationButton.getScene();
                String viewFile = "reservation-view.fxml";
                String titleName = "Reservation";
                System.out.println("current scene: " + currentScene);
                ViewUtils.changeScene(actionEvent,
                        viewFile,
                        titleName,
                        DomainConstants.HOTEL_APP_WINDOW_WIDTH,
                        DomainConstants.HOTEL_APP_WINDOW_HEIGHT,
                        currentScene);

            }
        });

         */
    }
}