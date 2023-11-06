package com.kyilmaz80.hotel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HotelApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HotelApplication.class.getResource(DomainConstants.HOTEL_VIEW_RESOURE_FILE));
        Scene scene = new Scene(fxmlLoader.load(), DomainConstants.HOTEL_APP_WINDOW_WIDTH, DomainConstants.HOTEL_APP_WINDOW_HEIGHT);
        stage.setTitle(DomainConstants.HOTEL_APP_TITLE_NAME);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}