package com.kyilmaz80.hotel.controllers;

import com.kyilmaz80.hotel.DomainConstants;
import com.kyilmaz80.hotel.ViewUtils;
import com.kyilmaz80.hotel.models.*;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class ReservationServiceController extends SceneController implements Initializable {

    @FXML
    private TableView<ReservationService> reservationServiceTableView;

    @FXML
    private TableColumn<ReservationService, Integer> reservationServiceId;

    @FXML
    private TableColumn<ReservationService, Integer> reservationServiceReservationId;

    @FXML
    private TableColumn<ReservationService, Integer> reservationServiceServiceId;

    @FXML
    private TableColumn<ReservationService, BigDecimal> reservationServiceUnitPrice;

    @FXML
    private TableColumn<ReservationService, Integer> reservationServiceQuantity;

    @FXML
    private ComboBox<Service> serviceComboBox;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Spinner<Integer> reservationServiceQuantitySpinner;

    private ServiceModel serviceModel;
    private ReservationServiceModel reservationServiceModel;
    private int reservationId;
    private int selectedServiceQuantity;

    @FXML
    private TextField reservationServicePriceTextField;

    private void initRoomComboBox() {
        ObservableList<Service> serviceObservable =  serviceModel.getServices();
        serviceComboBox.setItems(serviceObservable);
    }

    private void initSpinner() {
        reservationServiceQuantitySpinner.valueFactoryProperty()
                .set(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,
                DomainConstants.HOTEL_ROOM_MAX_SERVICE));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        serviceModel = new ServiceModel();
        serviceModel.selectAllServices("id, name");

        initRoomComboBox();
        initSpinner();

        selectedServiceQuantity = reservationServiceQuantitySpinner.getValue().intValue();

        // map to ReservationService
        reservationServiceId.
                setCellValueFactory(new PropertyValueFactory<ReservationService, Integer>("id"));
        reservationServiceReservationId.
                setCellValueFactory(new PropertyValueFactory<ReservationService, Integer>("reservation_id"));
        reservationServiceServiceId.
                setCellValueFactory(new PropertyValueFactory<ReservationService, Integer>("service_id"));
        reservationServiceUnitPrice.
                setCellValueFactory(new PropertyValueFactory<ReservationService, BigDecimal>("unit_price"));
        reservationServiceQuantity.
                setCellValueFactory(new PropertyValueFactory<ReservationService, Integer>("quantity"));

        reservationServiceModel = new ReservationServiceModel();

        // table view init
        reservationServiceModel.selectAllReservationServices();
        reservationServiceTableView.setItems(reservationServiceModel.getReservationServices());

        reservationServiceQuantitySpinner.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //from chatgpt
                Spinner<Integer> spinner = (Spinner<Integer>) mouseEvent.getSource();
                // Get the selected value from the spinner's value factory
                int selectedValue = spinner.getValue();
                System.out.println("Selected Value: " + selectedValue);
                selectedServiceQuantity = selectedValue;
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

                var selectedService = serviceComboBox.getValue();

                if (selectedService == null) {
                    ViewUtils.showAlert("No service item selected!");
                    return;
                }

                System.out.println("selected: " + selectedService);
                int selectedServiceId = selectedService.getId();
                System.out.println("Mapping service id " + selectedServiceId);

                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                ReservationService reservationService = (ReservationService) stage.getUserData();

                reservationId = reservationService.getReservation_id();

                System.out.println("reservation_id: " + reservationId);
                System.out.println("service_id: " + selectedServiceId);
                System.out.println("unit_price: " + reservationServicePriceTextField.getText());
                System.out.println("quantity: " + selectedServiceQuantity);

                Map<String, Object> reservationServiceInsertMap = new TreeMap<>();
                reservationServiceInsertMap.put("reservation_id", reservationId);
                reservationServiceInsertMap.put("service_id", selectedServiceId);
                reservationServiceInsertMap.put("unit_price", reservationServicePriceTextField.getText());
                reservationServiceInsertMap.put("quantity", selectedServiceQuantity);

                reservationServiceModel.insertReservationService(reservationServiceInsertMap);
                reservationServiceModel.selectAllReservationServices();
                reservationServiceTableView.setItems(reservationServiceModel.getReservationServices());
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("On click Delete");
                var selected = reservationServiceTableView.getSelectionModel().getSelectedItem();
                if (selected == null) {
                    ViewUtils.showAlert("No reservation service item selected!");
                    return;
                }
                System.out.println("selected: " + selected);
                var selectedId = selected.getId();
                System.out.println("Deleting id " + selectedId);

                reservationServiceModel.deleteReservationService(selectedId);
                reservationServiceModel.selectAllReservationServices();
                reservationServiceTableView.setItems(reservationServiceModel.getReservationServices());
            }
        });



    }

    private boolean validateInputs() {
        return true;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
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
