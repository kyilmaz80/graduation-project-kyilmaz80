package com.kyilmaz80.hotel.controllers;

import com.kyilmaz80.hotel.ViewUtils;
import com.kyilmaz80.hotel.models.RoomType;
import com.kyilmaz80.hotel.models.Room;
import com.kyilmaz80.hotel.models.RoomModel;
import com.kyilmaz80.hotel.utils.StringUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;

import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

public class RoomController extends SceneController implements Initializable {
    @FXML
    private TableView<Room> roomTableView;

    @FXML
    private TableColumn<Room, Integer> roomId;

    @FXML
    private TableColumn<Room, String> roomName;

    @FXML
    private TableColumn<Room, Integer> roomCapacity;

    @FXML
    private TableColumn<Room, BigDecimal> roomPrice;

    @FXML
    private TableColumn<Room, Integer> roomTypeId;

    @FXML
    private Button filterButton;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField roomNameTextField;

    @FXML
    private TextField roomCapacityTextField;

    @FXML
    private TextField roomPriceTextField;

    @FXML
    private ComboBox<RoomType> roomTypeComboBox;

    private RoomModel model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        filterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Map<String, Pair<String,String>> roomMapColumns = new HashMap<>();

                if (!validateInputs()) {
                    System.out.println("Inputs not valid!");
                    return;
                }

                if (!roomNameTextField.getText().isEmpty()) {
                    roomMapColumns.put("name", new Pair<String,String>(roomNameTextField.getText(), "LIKE"));
                    //roomMapColumns.put("name", roomNameTextField.getText());
                }

                if (!roomPriceTextField.getText().isEmpty()) {
                    roomMapColumns.put("price", new Pair<String,String>(roomPriceTextField.getText(), "="));
                    //roomMapColumns.put("price", roomPriceTextField.getText());
                }

                if (!roomCapacityTextField.getText().isEmpty()) {
                    roomMapColumns.put("capacity", new Pair<String,String>(roomCapacityTextField.getText(), "="));
                    //roomMapColumns.put("capacity", roomCapacityTextField.getText());
                }

                if (roomTypeComboBox.getValue() != null) {
                    int tid = roomTypeComboBox.getValue().getId();
                    System.out.println("room type id: " + tid);
                    if (tid != -1) {
                        //roomMapColumns.put("tid", String.valueOf(tid));
                        roomMapColumns.put("tid", new Pair<String,String>(String.valueOf(tid), "="));
                    }
                }

                if (roomMapColumns.isEmpty()) {
                    model.selectRoomListLike(roomNameTextField.getText());
                } else {
                    //model.selectRoomListFilter(mapColumns);
                    model.selectRoomListFilter2(roomMapColumns);
                }

                roomTableView.setItems(model.getRooms());
            }
        });

        roomTypeComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                var comboObj = (RoomType) roomTypeComboBox.getValue();
                System.out.println("On combo selected: " + " id: " +  comboObj.getId()  +
                        " " + roomTypeComboBox.getValue());
                System.out.println("Are all inputs entered? " + areAllInputsEntered());
                if(areAllInputsEntered()) {
                    addButton.setDisable(false);
                } else {
                    addButton.setDisable(true);
                }
            }

        });

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!validateInputs()) {
                    System.out.println("Inputs not valid!");
                    return;
                }
                Map<String, String> roomInsertMap = new TreeMap<>();
                String roomName = roomNameTextField.getText();
                String capacity = roomCapacityTextField.getText();
                String price = roomPriceTextField.getText();
                String tid = String.valueOf(roomTypeComboBox.getValue().getId());

                System.out.println("On add button");

                roomInsertMap.put("name", roomName);
                roomInsertMap.put("capacity", capacity);
                roomInsertMap.put("price", price);
                roomInsertMap.put("tid", tid);

                model.insertRoom(roomInsertMap);
                model.selectAllRooms();
                roomTableView.setItems(model.getRooms());
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("On click Delete");
                var selected = roomTableView.getSelectionModel().getSelectedItem();
                if (selected == null) {
                    ViewUtils.showAlert("No RoomFeature item selected!");
                    return;
                }
                System.out.println("selected: " + selected);
                var selectedId = selected.getId();
                System.out.println("Deleting id " + selectedId);

                model.deleteRoom(selectedId);
                model.selectAllRooms();
                roomTableView.setItems(model.getRooms());


            }
        });

        roomId.setCellValueFactory(new PropertyValueFactory<Room, Integer>("id"));
        roomName.setCellValueFactory((new PropertyValueFactory<Room, String>("name")));
        roomCapacity.setCellValueFactory((new PropertyValueFactory<Room, Integer>("capacity")));
        roomPrice.setCellValueFactory((new PropertyValueFactory<Room, BigDecimal>("price")));
        roomTypeId.setCellValueFactory((new PropertyValueFactory<Room, Integer>("tid")));

        model = new RoomModel();

        // table view init
        model.selectAllRooms();
        roomTableView.setItems(model.getRooms());

        String roomTypeColumns = "id,tname";
        // combo box init
        model.selectRoomTypesList2(roomTypeColumns);
        //roomTypeComboBox.getItems().addAll(model.getRoomTypes());
        ObservableList<RoomType> roomTypeObservable = model.getRoomTypes();
        //https://stackoverflow.com/questions/51689888/how-can-i-correctly-add-a-null-item-to-javafxs-combobox
        roomTypeObservable.add(new RoomType()); // add empty room type
        roomTypeComboBox.setItems(roomTypeObservable);
    }

    private boolean areAllInputsEntered() {
        return !roomNameTextField.getText().isEmpty() && !roomCapacityTextField.getText().isEmpty() &&
                !roomPriceTextField.getText().isEmpty() &&  roomTypeComboBox.getValue() != null ;
    }

    private boolean validateInputs() {
        String roomName;
        String roomCapacity;
        String roomPrice;
        int tid;

        System.out.println("On click Filter");
        roomName = roomNameTextField.getText();
        System.out.println("room name: " + roomName);
        roomCapacity = roomCapacityTextField.getText();
        System.out.println("room capacity: " + roomCapacity);
        roomPrice = roomPriceTextField.getText();
        System.out.println("room price: " + roomPrice);


        if (!roomName.isEmpty() && !StringUtils.inputValid1(roomName)) {
            System.err.println("RoomName Input not valid!");
            return false;
        }

        if (!roomPrice.isEmpty() & !StringUtils.inputValid2(roomPrice)) {
            System.err.println("RoomPrice Input not valid!");
            return false;
        }

        if (!roomCapacity.isEmpty() & !StringUtils.inputValid3(roomCapacity)) {
            System.err.println("RoomCapacity Input not valid!");
            return false;
        }


        return true;
    }
}
