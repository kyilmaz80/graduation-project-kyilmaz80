package com.kyilmaz80.hotel.controllers;

import com.kyilmaz80.hotel.DomainConstants;
import com.kyilmaz80.hotel.ViewUtils;
import com.kyilmaz80.hotel.utils.StringUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SceneController implements Initializable {
    @FXML
    private Button backButton;
    private Scene preScene;
    @FXML
    private AnchorPane anchorPane; //butun view fxml lerde bu anchorPane id si girilmeli!
    protected String selectedLabel;

    public void setPreScene(Scene preScene) {
        System.out.println("prescene: " + backButton.getScene());
        this.preScene = preScene;
    }

    @FXML
    void onClickBack(ActionEvent event) throws IOException {
        System.out.println("On click back");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        System.out.println(preScene);
        stage.setTitle(DomainConstants.HOTEL_APP_TITLE_NAME);
        stage.setScene(preScene);
        stage.show();
    }

    @FXML
    private void onEnterLabel(MouseEvent event) {
        Label label = (Label) event.getTarget();
        System.out.println("current scene: " + label.getScene());
        System.out.println(event.getTarget());
        if (event.getEventType().getName() == "MOUSE_ENTERED") {
            //Label label = (Label) event.getTarget();
            selectedLabel = label.getId();
            System.out.println(selectedLabel);
            label.setBackground(Background.fill(Paint.valueOf("green")));
        }
    }

    @FXML
    private void onEnterButton(MouseEvent event) {
        Button button = (Button) event.getTarget();
        System.out.println(event.getTarget());
        if (event.getEventType().getName() == "MOUSE_ENTERED") {
            selectedLabel = button.getId();
            System.out.println(selectedLabel);
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

    @FXML
    protected void openScene(Event event) {
        String viewName = selectedLabel.toLowerCase().
                replaceAll("label", "").
                replaceAll("button", "");
        //String viewName = "reservation";
        String viewFile = viewName + "-view.fxml";
        String titleName = StringUtils.toUpperFirstChar(viewName);
        System.out.println("view file: " + viewFile);
        Scene currentScene = anchorPane.getScene();
        System.out.println("current scene: " + currentScene);
        ViewUtils.changeScene(event,
                viewFile,
                titleName,
                DomainConstants.HOTEL_APP_WINDOW_WIDTH,
                DomainConstants.HOTEL_APP_WINDOW_HEIGHT,
                currentScene);
    }

    @FXML
    protected void openScene2(Event event, Object obj) {
        String viewName = selectedLabel.toLowerCase().
                replaceAll("label", "").
                replaceAll("button", "");
        //String viewName = "reservation";
        String viewFile = viewName + "-view.fxml";
        String titleName = StringUtils.toUpperFirstChar(viewName);
        System.out.println("view file: " + viewFile);
        Scene currentScene = anchorPane.getScene();
        System.out.println("current scene: " + currentScene);
        System.out.println("obj: " + obj);
        ViewUtils.changeScene2(event,
                viewFile,
                titleName,
                DomainConstants.HOTEL_APP_WINDOW_WIDTH,
                DomainConstants.HOTEL_APP_WINDOW_HEIGHT,
                currentScene,
                obj);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image(getClass().getResourceAsStream(DomainConstants.SCENE_BACK_ARROW_IMAGE));
        backButton.setGraphic(new ImageView(image));
    }
}
