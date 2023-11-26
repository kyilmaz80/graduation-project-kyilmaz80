package com.kyilmaz80.hotel.controllers;

import com.kyilmaz80.hotel.DomainConstants;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
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
    private TableColumn<ReservationView, String> reservationRoomId;

    @FXML
    private TableColumn<ReservationView, String> reservationRoomName;

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
    private Button buttonCheckedInOut;

    @FXML
    private ComboBox<Room> roomComboBox;

    @FXML
    private ComboBox<Customer> customerComboBox;

    @FXML
    private ComboBox<Customer> guestComboBox;

    @FXML
    private Spinner<Integer> roomCapacitySpinner;

    @FXML
    private DateTimePicker reservationCheckInDateTimePicker;

    @FXML
    private DateTimePicker reservationCheckedInDateTimePicker;

    @FXML
    private DateTimePicker reservationCheckedOutDateTimePicker;

    @FXML
    private DateTimePicker reservationCheckOutDateTimePicker;

    private RoomModel roomModel;
    private ReservationModel reservationModel;
    private ReservationViewModel reservationViewModel;
    private ReservationCustomerModel reservationCustomerModel;

    private int selectedRoomCapacity;
    private int selectedRoomId;
    private LocalDateTime reservationCheckIn;
    private LocalDateTime reservationCheckOut;



    private void initRoomComboBox() {
        ObservableList<Room>  roomObservable = null;
        if (selectedRoomCapacity == 1) {
            roomObservable = roomModel.getSingleRooms();
            guestComboBox.setDisable(true);
        } else if (selectedRoomCapacity == 2) {
            roomObservable = roomModel.getDoubleRooms();
            guestComboBox.setDisable(false);
        }
        roomComboBox.setItems(roomObservable);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);


        roomCapacitySpinner.valueFactoryProperty().set(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,
                DomainConstants.HOTEL_ROOM_MAX_CAPACITY));

        roomModel = new RoomModel();
        roomModel.selectAllRooms();

        selectedRoomCapacity = roomCapacitySpinner.getValue().intValue();

        initRoomComboBox();

        //customerComboBox
        CustomerModel customerModel = new CustomerModel();
        customerModel.selectAllCustomers();
        ObservableList<Customer> customerObservable = customerModel.getCustomers();
        customerComboBox.setItems(customerObservable);
        guestComboBox.setItems(customerObservable);


        // map to ReservationView
        reservationId.setCellValueFactory(new PropertyValueFactory<ReservationView, Integer>("id"));
        reservationRoomId.setCellValueFactory(new PropertyValueFactory<ReservationView, String>("room_id"));
        reservationRoomName.setCellValueFactory(new PropertyValueFactory<ReservationView, String>("room_name"));
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

        roomCapacitySpinner.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //from chatgpt
                Spinner<Integer> spinner = (Spinner<Integer>) mouseEvent.getSource();
                // Get the selected value from the spinner's value factory
                int selectedValue = spinner.getValue();
                System.out.println("Selected Value: " + selectedValue);
                selectedRoomCapacity = selectedValue;
                initRoomComboBox();
            }
        });

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
                selectedRoomId = selectedRoom.getId();
                System.out.println("Mapping room id " + selectedRoomId);

                var selectedCustomer = customerComboBox.getValue();
                if (selectedCustomer == null) {
                    ViewUtils.showAlert("No customer item selected!");
                    return;
                }

                System.out.println("selected customer: " + selectedCustomer);
                var selectedCustomerId = selectedCustomer.getId();
                System.out.println("Mapping customer id " + selectedCustomerId);

                var selectedGuest = guestComboBox.getValue();
                int selectedGuestId = -1;

                if (selectedGuest == null && selectedRoomCapacity == 2) {
                    ViewUtils.showAlert("No guest customer item selected!");
                    return;
                }
                if (selectedRoomCapacity == 2) {
                    System.out.println("selected guest customer: " + selectedGuest);
                    selectedGuestId = selectedGuest.getId();
                    System.out.println("Mapping guest customer id " + selectedGuestId);
                }


                reservationCheckIn = reservationCheckInDateTimePicker.getDateTimeValue();
                reservationCheckOut = reservationCheckOutDateTimePicker.getDateTimeValue();

                //TODO: fix
                if (!isSelectedRoomAvailable()) {
                    ViewUtils.showAlert("Selected room is not available for reservation! Please select another.");
                    return;
                }

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
                Map<String, Object> reservationGuestInsertMap = new TreeMap<>();

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

                if (selectedRoomCapacity == 2) {
                    if (selectedGuestId == -1) {
                        throw new RuntimeException("selectedGuestId -1 not expected");
                    }
                    reservationGuestInsertMap.put("customer_id", String.valueOf(selectedGuestId));
                    reservationGuestInsertMap.put("reservation_id", String.valueOf(lastReservationId));
                }


                reservationCustomerModel.insertReservationCustomer(reservationCustomerInsertMap);
                if (selectedRoomCapacity == 2) {
                    reservationCustomerModel.insertReservationCustomer(reservationGuestInsertMap);
                }
                reservationCustomerModel.selectAllReservationCustomers();
                reservationModel.selectAllReservations();
                reservationViewModel.selectAllReservations();
                reservationTableView.setItems(reservationViewModel.getReservations());

            }
        });


        buttonCheckedInOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                var selected = reservationTableView.getSelectionModel().getSelectedItem();
                if (selected == null) {
                    ViewUtils.showAlert("No reservation item selected!");
                    return;
                }
                System.out.println("selected: " + selected);
                var selectedId = selected.getId();
                System.out.println("checkedin: " + reservationCheckedInDateTimePicker.getValue());
                if (reservationCheckedInDateTimePicker.getValue() != null)  {
                    reservationModel.updateReservationCheckedIn(selectedId, reservationCheckedInDateTimePicker.getDateTimeValue());
                }

                if (reservationCheckedOutDateTimePicker.getValue() != null) {
                    reservationModel.updateReservationCheckedOut(selectedId, reservationCheckedOutDateTimePicker.getDateTimeValue());
                }

                reservationViewModel.selectAllReservations();
                reservationTableView.setItems(reservationViewModel.getReservations());


            }
        });


        roomComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                var comboObj = (Room) roomComboBox.getValue();
                if (comboObj != null) {
                    System.out.println("On combo selected: " + " id: " +  comboObj.getId()  +
                            " name: " + roomComboBox.getValue());
                }

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
        LocalDateTime reservationCheckIn = reservationCheckInDateTimePicker.getDateTimeValue();
        LocalDateTime reservationCheckOut = reservationCheckOutDateTimePicker.getDateTimeValue();
        LocalDateTime fromDateTime = LocalDateTime.from(reservationCheckIn);
        long hoursDiff = fromDateTime.until(reservationCheckOut, ChronoUnit.HOURS);
        long daysDiff = fromDateTime.until(reservationCheckOut, ChronoUnit.DAYS);
        System.out.println("Reservation days: " + daysDiff);
        System.out.println("Reservation hours: " + hoursDiff);

        if (reservationCheckIn.toLocalDate().getDayOfMonth() < LocalDate.now().getDayOfMonth()) {
            reservationCheckInDateTimePicker.setBorder(Border.stroke(Paint.valueOf("RED")));
            ViewUtils.showAlert("Check-in must be greater than " + LocalDateTime.now());
            return false;
        }
        reservationCheckInDateTimePicker.setBorder(Border.EMPTY);

        if (daysDiff < 0 || (daysDiff == 0 && hoursDiff == 0)) {
            reservationCheckOutDateTimePicker.setBorder(Border.stroke(Paint.valueOf("RED")));
            ViewUtils.showAlert("Check-out must be greater than Check-in!");
            return false;
        }
        reservationCheckOutDateTimePicker.setBorder(Border.EMPTY);

        if (daysDiff == 0 && hoursDiff < 24 ) {
            reservationCheckInDateTimePicker.setBorder(Border.stroke(Paint.valueOf("RED")));
            reservationCheckOutDateTimePicker.setBorder(Border.stroke(Paint.valueOf("RED")));
            ViewUtils.showAlert("No hour based Check-in!");
            return false;
        }
        reservationCheckInDateTimePicker.setBorder(Border.EMPTY);
        reservationCheckOutDateTimePicker.setBorder(Border.EMPTY);

        return true;
    }

    private boolean isSelectedRoomAvailable() {
        ObservableList<Reservation> reservations = reservationModel.getReservations();
        /*
        Date a, b;   // assume these are set to something
        Date d;      // the date in question

        return a.compareTo(d) * d.compareTo(b) >= 0; //inclusive =
         */
        LocalDateTime a;
        LocalDateTime b;
        LocalDateTime d = reservationCheckIn;
        LocalDateTime e = reservationCheckOut;

        for(Reservation reservation : reservations) {
            if (selectedRoomId == reservation.getRoom_id()) {
                a = reservation.getCheckin_date();
                b = reservation.getCheckout_date();

                if (reservation.getCheckedin_time() != null && reservation.getCheckedout_time() != null) {
                    System.out.println("Room " + reservation.getRoom_id() + " available. Already checked-out." );
                    return true;
                }
                System.out.println("Reservation Checkin in db: " + a);
                System.out.println("Reservation Checkout in db: " + b);
                System.out.println("Reservation check in in question: " + d);
                if(d.isEqual(a)) {
                    return false;
                }

                if (d.isBefore(a) && e.isBefore(b) & e.isAfter(a)) {
                    return false;
                }

                if (d.isAfter(a) && d.isBefore(b)) {
                //if (a.compareTo(d) * d.compareTo(b) >= 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean areAllInputsEntered() {
        //TODO:
        return true;
    }



}
