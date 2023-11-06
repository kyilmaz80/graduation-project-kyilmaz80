package com.kyilmaz80.hotel;

import com.kyilmaz80.hotel.utils.StringUtils;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;

public class HotelController {
    @FXML
    private Label roomsLabel;

    @FXML
    private Label featuresLabel;

    @FXML
    private Label servicesLabel;

    @FXML
    private Label customersLabel;

    private String selectedLabel;

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

    @FXML
    private void onEnterLabel(MouseEvent event) {
        System.out.println("current scene: " + roomsLabel.getScene());
        System.out.println(event.getTarget());
        if (event.getEventType().getName() == "MOUSE_ENTERED") {
            Label label = (Label) event.getTarget();
            selectedLabel = label.getId();
            System.out.println(selectedLabel);
            label.setBackground(Background.fill(Paint.valueOf("green")));
        }
    }

    @FXML
    private void onExitLabel(MouseEvent event) {
        System.out.println(event.getTarget());
        System.out.println(event.getEventType().getName());
        if (event.getEventType().getName() == "MOUSE_EXITED") {
            Label label = (Label) event.getTarget();
            System.out.println(label.getId());
            label.setBackground(Background.EMPTY);
        }
    }



}