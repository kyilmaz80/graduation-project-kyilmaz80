package com.kyilmaz80.hotel.controllers;

import com.kyilmaz80.hotel.models.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

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
    private TableColumn<Customer, String> reservationCustomerName;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private ComboBox<Room> roomComboBox;

    @FXML
    private ComboBox<Customer> customerComboBox;

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

        //roomNameComboBox
        //String roomColumns = "id,name";
        RoomModel roomModel = new RoomModel();
        //TODO: non-reserved rooms get
        roomModel.selectAllRooms();
        ObservableList<Room>  roomObservable = roomModel.getRooms();
        //roomObservable.add(new Room());
        roomComboBox.setItems(roomObservable);

        //customerComboBox
        CustomerModel customerModel = new CustomerModel();
        customerModel.selectAllCustomers();
        ObservableList<Customer> customerObservable = customerModel.getCustomers();
        customerComboBox.setItems(customerObservable);

        // map to reservation_view
        reservationId.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("id"));
        reservationRoomId.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("room_id"));
        reservationCheckInDate.setCellValueFactory(new PropertyValueFactory<Reservation, Timestamp>("checkin_date"));
        reservationCheckOutDate.setCellValueFactory(new PropertyValueFactory<Reservation, Timestamp>("checkout_date"));
        reservationCheckedInDate.setCellValueFactory(new PropertyValueFactory<Reservation, Timestamp>("checkedin_time"));
        reservationCheckedOutDate.setCellValueFactory(new PropertyValueFactory<Reservation, Timestamp>("checkedout_time"));
        reservationCustomerName.setCellValueFactory(new PropertyValueFactory<Customer, String>("customer_name"));

        model = new ReservationModel();

        // table view init
        model.selectAllReservations();
        reservationTableView.setItems(model.getReservations());




        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!validateInputs()) {
                    System.out.println("Inputs not valid!");
                    return;
                }

                Map<String, String> reservationInsertMap = new TreeMap<>();

            }
        });

        roomComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                var comboObj = (Room) roomComboBox.getValue();
                System.out.println("On combo selected: " + " id: " +  comboObj.getId()  +
                        " name: " + roomComboBox.getValue());
                System.out.println("Are all inputs entered? " + areAllInputsEntered());
                if(areAllInputsEntered()) {
                    addButton.setDisable(false);
                } else {
                    addButton.setDisable(true);
                }
            }

        });

    }

    private boolean validateInputs() {
        return true;
    }

    private boolean areAllInputsEntered() {

        return true;
    }



}
