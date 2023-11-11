package com.kyilmaz80.hotel;

import com.kyilmaz80.hotel.models.RoomTypes;
import com.kyilmaz80.hotel.models.Rooms;
import com.kyilmaz80.hotel.models.RoomsModel;
import com.kyilmaz80.hotel.utils.StringUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class RoomsController extends SceneController implements Initializable {
    @FXML
    private TableView<Rooms> roomsTableView;

    @FXML
    private TableColumn<Rooms, Integer> roomsId;

    @FXML
    private TableColumn<Rooms, String> roomsName;

    @FXML
    private TableColumn<Rooms, Integer> roomsCapacity;

    @FXML
    private TableColumn<Rooms, Double> roomsPrice;

    @FXML
    private TableColumn<Rooms, Integer> roomsTypeId;

    @FXML
    private Button filterButton;

    @FXML
    private TextField roomNameTextField;

    @FXML
    private TextField roomCapacityTextField;

    @FXML
    private TextField roomPriceTextField;

    @FXML
    private ComboBox<RoomTypes> roomTypeComboBox;

    private RoomsModel model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        filterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("On click Filter");
                String roomName = roomNameTextField.getText();
                System.out.println("room name: " + roomName);
                String roomCapacity = roomCapacityTextField.getText();
                System.out.println("room capacity: " + roomCapacity);
                String roomPrice = roomPriceTextField.getText();
                System.out.println("room price: " + roomPrice);

                //String featurePrice = featurePriceTextField.getText();
                if (!StringUtils.inputValid1(roomName)) {
                    System.err.println("RoomName Input not valid!");
                    return;
                }

                if (!roomPrice.isEmpty() & !StringUtils.inputValid2(roomPrice)) {
                    System.err.println("RoomPrice Input not valid!");
                    return;
                }

                if (!roomCapacity.isEmpty() & !StringUtils.inputValid3(roomCapacity)) {
                    System.err.println("RoomCapacity Input not valid!");
                    return;
                }


                /*
                if (!StringUtils.inputValid2(featurePrice)) {
                    System.err.println("FeaturePrice Input not valid!");
                    return;
                }

                if (!featurePrice.isEmpty()) {
                    System.out.println("Feature Price not empty");
                    model.selectFeatureListLikeFilter(featureName, featurePrice);
                } else {
                    model.selectFeatureListLike(featureName);
                }
                 */
                Map<String,String> mapColumns = new HashMap<String,String>();
                mapColumns.put("price", roomPrice);
                if (!roomPrice.isEmpty()) {

                    model.selectRoomsListLikeFilter(mapColumns);
                } else if (!roomCapacity.isEmpty()) {
                    model.selectRoomsListLikeFilter("capacity", roomCapacity);
                } else {
                    model.selectRoomsListLike(roomName);
                }

                roomsTableView.setItems(model.getRooms());
            }
        });

        roomTypeComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                var comboObj = (RoomTypes) roomTypeComboBox.getValue();
                System.out.println("On combo selected: " + " id: " +  comboObj.getRoomTypeId()  +
                        " " + roomTypeComboBox.getValue());
            }

        });

        roomsId.setCellValueFactory(new PropertyValueFactory<Rooms, Integer>("id"));
        roomsName.setCellValueFactory((new PropertyValueFactory<Rooms, String>("name")));
        roomsCapacity.setCellValueFactory((new PropertyValueFactory<Rooms, Integer>("capacity")));
        roomsPrice.setCellValueFactory((new PropertyValueFactory<Rooms, Double>("price")));
        roomsTypeId.setCellValueFactory((new PropertyValueFactory<Rooms, Integer>("tid")));

        model = new RoomsModel();

        // table view init
        model.selectAllRooms();
        roomsTableView.setItems(model.getRooms());

        // combo box init
        model.selectRoomTypesList();
        //roomTypeComboBox.getItems().addAll(model.getRoomTypes());
        roomTypeComboBox.setItems(model.getRoomTypes());
    }
}
