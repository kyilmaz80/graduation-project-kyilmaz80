package com.kyilmaz80.hotel.controllers;

import com.kyilmaz80.hotel.DomainConstants;
import com.kyilmaz80.hotel.ViewUtils;
import com.kyilmaz80.hotel.models.Customer;
import com.kyilmaz80.hotel.models.ReservationView;
import com.kyilmaz80.hotel.models.ReservationViewModel;
import com.kyilmaz80.hotel.utils.StringUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;
import javafx.util.Pair;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class HotelController extends SceneController implements Initializable {
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
    protected Label roomsLabel;

    @FXML
    private Label featuresLabel;

    @FXML
    private Label servicesLabel;

    @FXML
    private Label customersLabel;

    @FXML
    private Button reservationButton;

    @FXML
    private DatePicker reservationFromDate;

    @FXML
    private DatePicker reservationToDate;

    @FXML
    private TextField reservationSearchTextField;

    @FXML
    private Button filterButton;

    private ReservationViewModel reservationViewModel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // map to ReservationView
        reservationId.setCellValueFactory(new PropertyValueFactory<ReservationView, Integer>("id"));
        reservationRoomId.setCellValueFactory(new PropertyValueFactory<ReservationView, Integer>("room_id"));
        reservationCheckInDate.setCellValueFactory(new PropertyValueFactory<ReservationView, Timestamp>("checkin_date"));
        reservationCheckOutDate.setCellValueFactory(new PropertyValueFactory<ReservationView, Timestamp>("checkout_date"));
        reservationCheckedInDate.setCellValueFactory(new PropertyValueFactory<ReservationView, Timestamp>("checkedin_time"));
        reservationCheckedOutDate.setCellValueFactory(new PropertyValueFactory<ReservationView, Timestamp>("checkedout_time"));
        reservationCustomerName.setCellValueFactory(new PropertyValueFactory<Customer, String>("customer_name"));

        reservationViewModel = new ReservationViewModel();
        // table view init
        reservationViewModel.selectAllReservations();
        reservationTableView.setItems(reservationViewModel.getReservations());

        filterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("On click Filter");
                String searchFilter = reservationSearchTextField.getText();
                if (!StringUtils.inputValid1(searchFilter)) {
                    System.err.println("Search Input not valid!");
                    return;
                }

                Map<String, Pair<String,String>> reservationViewMapColumns = new HashMap<>();

                /*
                Map<String, Pair<String,String>> reservationMapColumns = new HashMap<>();
                //roomMapColumns.put("name", new Pair<String,String>(roomNameTextField.getText(), "LIKE"));
                // SELECT * FROM ReservationView WHERE checkin_date BETWEEN ? and ?
                reservationMapColumns.put("checkin_date", new Pair<String,String>)
                 */
                LocalDate reservationFrom = reservationFromDate.getValue();
                LocalDate reservationTo = reservationToDate.getValue();

                if (reservationFrom == null && reservationTo == null) {
                    reservationViewModel.selectAllReservations();
                    reservationTableView.setItems(reservationViewModel.getReservations());
                    return;
                } else if (reservationTo == null) {
                    reservationTo = LocalDate.now();
                }


                reservationViewModel.selectReservationsBetweenTwo(reservationFrom, reservationTo);
                String reservationSearch = reservationSearchTextField.getText();
                if (!reservationSearch.isEmpty()) {
                    reservationViewMapColumns.put("customer_name", new Pair<String,String>(reservationSearch, "LIKE"));
                    reservationViewModel.selectReservationListOrFilter(reservationViewMapColumns);
                }

                reservationTableView.setItems(reservationViewModel.getReservations());
            }
        });
    }
}