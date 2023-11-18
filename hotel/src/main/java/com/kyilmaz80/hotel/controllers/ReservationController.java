package com.kyilmaz80.hotel.controllers;

import com.kyilmaz80.hotel.DomainConstants;
import com.kyilmaz80.hotel.ViewUtils;
import com.kyilmaz80.hotel.models.Reservation;
import com.kyilmaz80.hotel.models.ReservationModel;
import com.kyilmaz80.hotel.models.Room;
import com.kyilmaz80.hotel.utils.StringUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class ReservationController extends SceneController implements Initializable {

    @FXML
    private TableView<Reservation> reservationTableView;

    @FXML
    private TableColumn<Reservation, Integer> reservationId;

    @FXML
    private TableColumn<Reservation, Integer> reservationRoomId;

    @FXML
    private TableColumn<Reservation, Timestamp>  reservationCheckInDate;

    @FXML
    private TableColumn<Reservation, Timestamp> reservationCheckOutDate;

    @FXML
    private TableColumn<Reservation, Timestamp> reservationCheckedInDate;

    @FXML
    private TableColumn<Reservation, Timestamp> reservationCheckedOutDate;

    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;

    @FXML
    private ComboBox<Room> roomNameComboBox;

    @FXML
    private DatePicker reservationCheckInDatePicker;

    @FXML
    private DatePicker reservationCheckOutDatePicker;

    @FXML
    private DatePicker reservationCheckedInDatePicker;

    @FXML
    private DatePicker reservationCheckedOutDatePicker;

    private ReservationModel model;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
    }



}
