package com.kyilmaz80.hotel.controllers;

import com.kyilmaz80.hotel.models.Customer;
import com.kyilmaz80.hotel.models.CustomerModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigInteger;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class CustomerController extends SceneController implements Initializable {

    @FXML
    private TableView<Customer> customerTableView;

    @FXML
    private TableColumn<Customer, Integer> customerId;

    @FXML
    private TableColumn<Customer, String> customerFullName;

    @FXML
    private TableColumn<Customer, BigInteger> customerIdentityNumber;

    @FXML
    private TableColumn<Customer, String> customerPhoneNumber;

    @FXML
    private TableColumn<Customer, Date> customerBirthDate;

    @FXML
    private TableColumn<Customer, String> customerDescription;

    private CustomerModel model;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        customerId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        customerFullName.setCellValueFactory(new PropertyValueFactory<Customer, String>("full_name"));
        customerIdentityNumber.setCellValueFactory(new PropertyValueFactory<Customer, BigInteger>("identity_number"));
        customerPhoneNumber.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone_number"));
        customerBirthDate.setCellValueFactory(new PropertyValueFactory<Customer, Date>("birth_date"));
        customerDescription.setCellValueFactory(new PropertyValueFactory<Customer, String>("detail"));

        model = new CustomerModel();
        // table view init
        String customerColumns = "id, full_name, identity_number, phone_number, birth_date, description";
        model.selectAllCustomers(customerColumns);
        customerTableView.setItems(model.getCustomers());

    }
}
