package com.kyilmaz80.hotel.controllers;

import com.kyilmaz80.hotel.ViewUtils;
import com.kyilmaz80.hotel.models.*;
import com.kyilmaz80.hotel.utils.DateTimePicker;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class ReservationViewController extends SceneController implements Initializable {

    @FXML
    private TableView<ReservationView> reservationTableView;

    @FXML
    private TableColumn<ReservationView, Integer> reservationId;

    @FXML
    private TableColumn<ReservationView, Integer> reservationRoomId;

    @FXML
    private TableColumn<ReservationView, Timestamp>  reservationCheckInDate;

    @FXML
    private TableColumn<ReservationView, Timestamp> reservationCheckOutDate;

    @FXML
    private TableColumn<ReservationView, Timestamp> reservationCheckedInDate;

    @FXML
    private TableColumn<ReservationView, Timestamp> reservationCheckedOutDate;

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
    private DateTimePicker reservationCheckInDatePicker;

    @FXML
    private DateTimePicker reservationCheckOutDatePicker;

    @FXML
    private DateTimePicker reservationCheckedInDatePicker;

    @FXML
    private DateTimePicker reservationCheckedOutDatePicker;

    private ReservationModel reservationModel;
    private ReservationViewModel reservationViewModel;
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

        // map to ReservationView
        reservationId.setCellValueFactory(new PropertyValueFactory<ReservationView, Integer>("id"));
        reservationRoomId.setCellValueFactory(new PropertyValueFactory<ReservationView, Integer>("room_id"));
        reservationCheckInDate.setCellValueFactory(new PropertyValueFactory<ReservationView, Timestamp>("checkin_date"));
        reservationCheckOutDate.setCellValueFactory(new PropertyValueFactory<ReservationView, Timestamp>("checkout_date"));
        reservationCheckedInDate.setCellValueFactory(new PropertyValueFactory<ReservationView, Timestamp>("checkedin_time"));
        reservationCheckedOutDate.setCellValueFactory(new PropertyValueFactory<ReservationView, Timestamp>("checkedout_time"));
        reservationCustomerName.setCellValueFactory(new PropertyValueFactory<Customer, String>("customer_name"));


        reservationModel = new ReservationModel();
        reservationModel.selectAllReservations();

        reservationViewModel = new ReservationViewModel();

        // table view init
        reservationViewModel.selectAllReservations();
        reservationTableView.setItems(reservationViewModel.getReservations());

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

                LocalDateTime reservationCheckIn = reservationCheckInDatePicker.getDateTimeValue();
                LocalDateTime reservationCheckOut = reservationCheckOutDatePicker.getDateTimeValue();

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

                //reservationViewModel.insertReservation(reservationInsertMap);
                reservationModel.insertReservation(reservationInsertMap);
                reservationModel.selectAllReservations();
                //reservationViewModel.selectAllReservations();

                Map<String, Object> reservationCustomerInsertMap = new TreeMap<>();

                ObservableList<Reservation> reservations;
                Reservation lastReservation = null;
                reservations = reservationModel.getReservations();
                if (reservations.size() != 0) {
                    lastReservation = reservations.getLast();
                }
                int lastReservationId = -1;
                if (lastReservation != null) {
                    lastReservationId = lastReservation.getId();
                }
                reservationCustomerInsertMap.put("customer_id", String.valueOf(selectedCustomerId));
                reservationCustomerInsertMap.put("reservation_id", String.valueOf(lastReservationId));

                reservationCustomerModel.insertReservationCustomer(reservationCustomerInsertMap);
                reservationCustomerModel.selectAllReservationCustomers();
                reservationModel.selectAllReservations();
                reservationViewModel.selectAllReservations();
                reservationTableView.setItems(reservationViewModel.getReservations());

            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("On click Delete");
                var selected = reservationTableView.getSelectionModel().getSelectedItem();
                if (selected == null) {
                    ViewUtils.showAlert("No reservation item selected!");
                    return;
                }
                System.out.println("selected: " + selected);
                var selectedId = selected.getId();
                System.out.println("Deleting id " + selectedId);

                reservationCustomerModel.deleteReservationCustomerByReservationId(selectedId);
                reservationCustomerModel.selectAllReservationCustomers();
                //reservationViewModel.deleteReservation(selectedId);
                reservationModel.deleteReservation(selectedId);
                reservationViewModel.selectAllReservations();
                reservationModel.selectAllReservations();
                reservationTableView.setItems(reservationViewModel.getReservations());



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
        LocalDateTime reservationCheckIn = reservationCheckInDatePicker.getDateTimeValue();
        LocalDateTime reservationCheckOut = reservationCheckOutDatePicker.getDateTimeValue();
        LocalDateTime fromDateTime = LocalDateTime.from(reservationCheckIn);
        long hours = fromDateTime.until(reservationCheckOut, ChronoUnit.HOURS);
        long days = fromDateTime.until(reservationCheckOut, ChronoUnit.DAYS);
        System.out.println("Reservation days: " + days);
        System.out.println("Reservation hours: " + hours);
        if (days < 0) {
            ViewUtils.showAlert("Check-out must be greater than Check-in!");
            return false;
        }
        if (days <=1 && hours < 24 ) {
            ViewUtils.showAlert("No hour based Check-in!");
            return false;
        }

        return true;
    }

    private boolean areAllInputsEntered() {

        return true;
    }



}
