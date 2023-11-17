package com.kyilmaz80.hotel.controllers;

import com.kyilmaz80.hotel.ViewUtils;
import com.kyilmaz80.hotel.models.Customer;
import com.kyilmaz80.hotel.models.CustomerModel;
import com.kyilmaz80.hotel.utils.StringUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Date;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class CustomerController extends SceneController implements Initializable {

    @FXML
    private TableView<Customer> customerTableView;

    @FXML
    private TableColumn<Customer, Integer> customerId;

    @FXML
    private TableColumn<Customer, String> customerFullName;

    @FXML
    private TableColumn<Customer, String> customerIdentityNumber;

    @FXML
    private TableColumn<Customer, String> customerPhoneNumber;

    @FXML
    private TableColumn<Customer, Date> customerBirthDate;

    @FXML
    private TableColumn<Customer, String> customerDescription;

    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;

    @FXML
    private TextField customerFullNameTextField;

    @FXML
    private TextField customerIdentityNumberTextField;

    @FXML
    private TextField customerPhoneNumberTextField;

    @FXML
    private DatePicker customerBirthDateDatePicker;

    @FXML
    private TextArea customerDescriptionTextArea;

    private CustomerModel model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        customerId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        customerFullName.setCellValueFactory(new PropertyValueFactory<Customer, String>("full_name"));
        customerIdentityNumber.setCellValueFactory(new PropertyValueFactory<Customer, String>("identity_number"));
        customerPhoneNumber.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone_number"));
        customerBirthDate.setCellValueFactory(new PropertyValueFactory<Customer, Date>("birth_date"));
        customerDescription.setCellValueFactory(new PropertyValueFactory<Customer, String>("description"));

        model = new CustomerModel();
        // table view init
        String customerColumns = "id, full_name, identity_number, phone_number, birth_date, description";
        model.selectAllCustomers(customerColumns);
        customerTableView.setItems(model.getCustomers());

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!areAllInputsEntered()) {
                    System.out.println("Input necessary");
                    return;
                }

                if (!validateInputs()) {
                    System.out.println("Inputs not valid!");
                    return;
                }

                Map<String, String> customerInsertMap = new TreeMap<>();

                String customerName = customerFullNameTextField.getText();
                String customerId = customerIdentityNumberTextField.getText();
                String customerPhone = customerPhoneNumberTextField.getText();
                Date customerBirth = Date.valueOf(customerBirthDateDatePicker.getValue());
                String customerDesc = customerDescriptionTextArea.getText();

                System.out.println(customerName);
                System.out.println(customerId);
                System.out.println(customerPhone);
                System.out.println(customerBirth);
                System.out.println(customerDesc);

                customerInsertMap.put("birth_date", customerBirth.toString());
                customerInsertMap.put("description", customerDesc);
                customerInsertMap.put("full_name", customerName);
                customerInsertMap.put("identity_number", customerId);
                customerInsertMap.put("phone_number", customerPhone);

                model.insertCustomer(customerInsertMap);
                model.selectAllCustomers(customerColumns);
                customerTableView.setItems(model.getCustomers());
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("On click Delete");
                var selected = customerTableView.getSelectionModel().getSelectedItem();
                if (selected == null) {
                    ViewUtils.showAlert("No Customer item selected!");
                    return;
                }
                System.out.println("selected: " + selected);
                var selectedId = selected.getId();
                System.out.println("Deleting id " + selectedId);

                model.deleteCustomer(selectedId);
                model.selectAllCustomers(customerColumns);
                customerTableView.setItems(model.getCustomers());

            }
        });

    }

    private boolean areAllInputsEntered() {
        return !customerFullName.getText().isEmpty() && !customerIdentityNumberTextField.getText().isEmpty();
    }

    private boolean validateInputs() {
        String customerName = customerFullNameTextField.getText();
        String customerId = customerIdentityNumberTextField.getText();
        String customerPhone = customerPhoneNumberTextField.getText();
        String customerDesc = customerDescriptionTextArea.getText();

        if (!customerName.isEmpty() && !StringUtils.inputValid7(customerName)) {
            System.err.println("CustomerName Input not valid!");
            return false;
        }

        if (!customerId.isEmpty() && !StringUtils.inputValid4(customerId)) {
            System.err.println("Customer ID Input not valid! Must be 11 characters!");
            return false;
        }

        if (!customerPhone.isEmpty() && !StringUtils.inputValid5(customerPhone)) {
            System.err.println("Customer Phone Input not valid! Must be 10 characters!");
            return false;
        }

        if (!customerDesc.isEmpty() && !StringUtils.inputValid6(customerDesc)) {
            System.err.println("Customer Desc Input not valid! Invalid characters!");
            return false;
        }


        return true;
    }
    @FXML
    private void onEnterButton(MouseEvent event) {

        System.out.println(event.getTarget());
        if (event.getEventType().getName() == "MOUSE_ENTERED") {
            Button button = (Button) event.getTarget();
            System.out.println(button.getText());
        }

    }
    @FXML
    private void handleButton() {
        deleteButton.setOnMouseClicked((event) -> {

            System.out.println("Are all inputs entered? " + areAllInputsEntered());
            if(areAllInputsEntered()) {
                addButton.setDisable(false);
            } else {
                addButton.setDisable(true);
            }

        });


    }


}
