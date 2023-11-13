package com.kyilmaz80.hotel;

import com.kyilmaz80.hotel.models.RoomTypes;
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

import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

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
    private ComboBox<RoomTypes> roomTypeComboBox;

    private RoomModel model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        filterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Map<String,String> mapColumns = new HashMap<String,String>();

                if (!validateInputs()) {
                    System.out.println("Inputs not valid!");
                    return;
                }


                if (!roomNameTextField.getText().isEmpty()) {
                    mapColumns.put("name", roomNameTextField.getText());
                }

                if (!roomPriceTextField.getText().isEmpty()) {
                    mapColumns.put("price", roomPriceTextField.getText());
                }

                if (!roomCapacityTextField.getText().isEmpty()) {
                    mapColumns.put("capacity", roomCapacityTextField.getText());
                }


                if (roomTypeComboBox.getValue() != null) {
                    int tid = roomTypeComboBox.getValue().getRoomTypeId();
                    System.out.println("room type id: " + tid);
                    if (tid != -1) {
                        mapColumns.put("tid", String.valueOf(tid));
                    }
                }



                if (mapColumns.isEmpty()) {
                    model.selectRoomListLike(roomNameTextField.getText());
                } else {
                    //model.selectRoomListFilter(mapColumns);
                    model.selectRoomListFilter2(mapColumns);
                }

                roomTableView.setItems(model.getRooms());
            }
        });

        roomTypeComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                var comboObj = (RoomTypes) roomTypeComboBox.getValue();
                System.out.println("On combo selected: " + " id: " +  comboObj.getRoomTypeId()  +
                        " " + roomTypeComboBox.getValue());
                System.out.println("Are all inputs entered? " + areAllInputsEntered());
                if(areAllInputsEntered()) {
                    addButton.setDisable(false);
                }
            }

        });

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("On add button");
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

        // combo box init
        model.selectRoomTypesList();
        //roomTypeComboBox.getItems().addAll(model.getRoomTypes());
        ObservableList<RoomTypes> roomTypesObservable = model.getRoomTypes();
        //https://stackoverflow.com/questions/51689888/how-can-i-correctly-add-a-null-item-to-javafxs-combobox
        roomTypesObservable.add(new RoomTypes()); // add empty room type
        roomTypeComboBox.setItems(roomTypesObservable);
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
