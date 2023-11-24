package com.kyilmaz80.hotel.controllers;

import com.kyilmaz80.hotel.DomainConstants;
import com.kyilmaz80.hotel.models.ReservationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReservationServiceController extends SceneController implements Initializable {

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    private int reservationId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);


    }

    @FXML
    private void onClickAnchor(MouseEvent event) {
        System.out.println("On click anchor");
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        ReservationService reservationService = (ReservationService) stage.getUserData();
        System.out.println("id from previous scene: " + reservationService.getReservation_id());
    }

}
