package com.kyilmaz80.hotel.controllers;

import com.kyilmaz80.hotel.ViewUtils;
import com.kyilmaz80.hotel.models.Service;
import com.kyilmaz80.hotel.models.ServiceModel;
import com.kyilmaz80.hotel.utils.StringUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;


public class ServiceController extends SceneController implements Initializable {

    @FXML
    private TableView<Service> serviceTableView;

    @FXML
    private TableColumn<Service, Integer> serviceId;

    @FXML
    private TableColumn<Service, String> serviceName;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField serviceNameTextField;

    private ServiceModel model;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        serviceId.setCellValueFactory(new PropertyValueFactory<Service, Integer>("id"));
        serviceName.setCellValueFactory(new PropertyValueFactory<Service, String>("name"));

        //TODO: update edit data
        // https://stackoverflow.com/questions/41465181/tableview-update-database-on-edit
        serviceName.setCellFactory(TextFieldTableCell.forTableColumn());
        serviceName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Service, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Service, String> serviceStringCellEditEvent) {
                var items = serviceStringCellEditEvent.getTableView().getItems();
                var row = items.get(serviceStringCellEditEvent.getTablePosition().getRow());
                row.setName(serviceStringCellEditEvent.getNewValue());

            }
        });

        model = new ServiceModel();
        // table view init
        String serviceColumns = "id, name";
        model.selectAllServices(serviceColumns);
        serviceTableView.setItems(model.getServices());

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (!validateInputs()) {
                    System.out.println("Inputs not valid!");
                    return;
                }

                String serviceName = serviceNameTextField.getText();
                System.out.println(serviceName);

                Map<String, Object> serviceInsertMap = new TreeMap<>();
                serviceInsertMap.put("name", serviceName);

                model.insertService(serviceInsertMap);
                model.selectAllServices(serviceColumns);
                serviceTableView.setItems(model.getServices());

            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("On click Delete");
                var selected = serviceTableView.getSelectionModel().getSelectedItem();
                if (selected == null) {
                    ViewUtils.showAlert("No Service item selected!");
                    return;
                }
                System.out.println("selected: " + selected);
                var selectedId = selected.getId();
                System.out.println("Deleting id " + selectedId);

                model.deleteService(selectedId);
                model.selectAllServices(serviceColumns);
                serviceTableView.setItems(model.getServices());

            }
        });

    }

    private boolean validateInputs() {
        String serviceName = serviceNameTextField.getText();

        if (!serviceName.isEmpty() && !StringUtils.inputValid7(serviceName)) {
            System.err.println("ServiceName Input not valid!");
            return false;
        }

        return true;
    }
}
