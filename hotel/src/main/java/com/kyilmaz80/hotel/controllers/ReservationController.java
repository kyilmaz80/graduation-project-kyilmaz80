package com.kyilmaz80.hotel.controllers;

import com.kyilmaz80.hotel.ViewUtils;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    private ReservationModel reservationModel;
    private ReservationCustomerModel reservationCustomerModel;


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

        reservationModel = new ReservationModel();

        // table view init
        reservationModel.selectAllReservations();
        reservationTableView.setItems(reservationModel.getReservations());

        reservationCustomerModel = new ReservationCustomerModel();
        reservationCustomerModel.selectAllReservationCustomers();


        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!validateInputs()) {
                    System.out.println("Inputs not valid!");
                    return;
                }

                System.out.println("On click Add");

                var selectedRoom = roomComboBox.getValue();
                if (selectedRoom == null) {
                    ViewUtils.showAlert("No room item selected!");
                    return;
                }
                System.out.println("selected: " + selectedRoom);
                int selectedRoomId = selectedRoom.getId();
                System.out.println("Mapping room id " + selectedRoomId);

                var selectedCustomer = customerComboBox.getValue();
                if (selectedCustomer == null) {
                    ViewUtils.showAlert("No customer item selected!");
                    return;
                }

                System.out.println("selected customer: " + selectedCustomer);
                var selectedCustomerId = selectedCustomer.getId();
                System.out.println("Mapping customer id " + selectedCustomerId);

                LocalDate reservationCheckIn = reservationCheckInDatePicker.getValue();
                LocalDate reservationCheckOut = reservationCheckOutDatePicker.getValue();

                Map<String, Object> reservationInsertMap = new TreeMap<>();
                reservationInsertMap.put("room_id", selectedRoomId);
                reservationInsertMap.put("checkin_date", reservationCheckIn.toString());
                reservationInsertMap.put("checkout_date", reservationCheckOut.toString());

                /*
                if (reservationId.getId() != null) {
                    newReservationId = Integer.parseInt(reservationId.getId()) + 1;
                } else {
                    System.err.println("last reservationId null!");
                    throw new RuntimeException();
                }

                 */
                //reservationCustomerModel.selectAllReservationCustomers();

                System.out.println(selectedRoomId);
                System.out.println(reservationCheckIn);
                System.out.println(reservationCheckOut);

                reservationModel.insertReservation(reservationInsertMap);
                reservationModel.selectAllReservations();

                Map<String, Object> reservationCustomerInsertMap = new TreeMap<>();

                ObservableList<Reservation> reservations;
                Reservation lastReservation = null;
                reservations = reservationModel.getReservations();
                if (reservations.size() != 0) {
                    lastReservation = reservations.getLast();
                }
                int newReservationId = 1;
                if (lastReservation != null) {
                    newReservationId = lastReservation.getId() + 1;
                }
                reservationCustomerInsertMap.put("customer_id", String.valueOf(selectedCustomerId));
                reservationCustomerInsertMap.put("reservation_id", String.valueOf(newReservationId));

                reservationCustomerModel.insertReservationCustomer(reservationCustomerInsertMap);
                reservationCustomerModel.selectAllReservationCustomers();
                reservationModel.selectAllReservations();
                reservationTableView.setItems(reservationModel.getReservations());

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
