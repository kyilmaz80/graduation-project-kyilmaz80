package com.kyilmaz80.hotel.controllers;

import com.kyilmaz80.hotel.ViewUtils;
import com.kyilmaz80.hotel.models.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

public class RoomFeatureController extends SceneController implements Initializable {

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
    private TableView<Feature> featureTableView;
    @FXML
    private TableColumn<Feature, Integer> featureId;

    @FXML
    private TableColumn<Feature, String> featureName;

    @FXML
    private TableColumn<Feature, Double> featurePrice;

    @FXML
    private TableView<RoomFeature> roomFeatureTableView;

    @FXML
    private TableColumn<RoomFeature, Integer> roomFeatureId;

    @FXML
    private TableColumn<RoomFeature, Integer> roomFeatureRoomId;

    @FXML
    private TableColumn<RoomFeature, Integer> roomFeatureFeatureId;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    private RoomFeatureModel roomFeatureModel;
    private RoomModel roomModel;
    private FeatureModel featureModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        roomId.setCellValueFactory(new PropertyValueFactory<Room, Integer>("id"));
        roomName.setCellValueFactory((new PropertyValueFactory<Room, String>("name")));
        roomCapacity.setCellValueFactory((new PropertyValueFactory<Room, Integer>("capacity")));
        roomPrice.setCellValueFactory((new PropertyValueFactory<Room, BigDecimal>("price")));
        roomTypeId.setCellValueFactory((new PropertyValueFactory<Room, Integer>("tid")));

        roomModel = new RoomModel();

        // table view init
        roomModel.selectAllRooms();
        roomTableView.setItems(roomModel.getRooms());

        featureId.setCellValueFactory(new PropertyValueFactory<Feature, Integer>("id"));
        featureName.setCellValueFactory((new PropertyValueFactory<Feature, String>("name")));
        featurePrice.setCellValueFactory((new PropertyValueFactory<Feature, Double>("price")));

        featureModel = new FeatureModel();
        featureModel.selectAllFeatures();
        featureTableView.setItems(featureModel.getFeatures());
        featureTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        roomFeatureId.setCellValueFactory(new PropertyValueFactory<RoomFeature, Integer>("id"));
        roomFeatureRoomId.setCellValueFactory(new PropertyValueFactory<RoomFeature, Integer>("room_id"));
        roomFeatureFeatureId.setCellValueFactory(new PropertyValueFactory<RoomFeature, Integer>("feature_id"));

        roomFeatureModel = new RoomFeatureModel();
        String roomFeatureColumns = "id, room_id, feature_id";
        roomFeatureModel.selectAllRoomFeatures(roomFeatureColumns);
        roomFeatureTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        roomFeatureTableView.setItems(roomFeatureModel.getRoomFeatures());

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("On click Add");
                var selectedRoom = roomTableView.getSelectionModel().getSelectedItem();
                if (selectedRoom == null) {
                    ViewUtils.showAlert("No room item selected!");
                    return;
                }
                System.out.println("selected: " + selectedRoom);
                var selectedRoomId = selectedRoom.getId();
                System.out.println("Mapping room id " + selectedRoomId);

                var selectedFeatures = featureTableView.getSelectionModel().getSelectedItems();
                if (selectedFeatures.size() == 0) {
                    ViewUtils.showAlert("No feature item selected!");
                    return;
                }

                Map<String, String> roomFeatureInsertMap = new TreeMap<>();
                //roomFeatureInsertMap.put("room_id", String.valueOf(selectedRoomId));

                System.out.println("selected: " + selectedFeatures);
                ArrayList<String> featureList = new ArrayList<>();
                for (Feature feature : selectedFeatures) {
                    var selectedFeatureId = feature.getId();
                    System.out.println("Feature id " + selectedFeatureId);
                    featureList.add(String.valueOf(selectedFeatureId));
                }
                System.out.println("selected features: " + featureList);

                for (String featureId: featureList) {
                    // insert
                    roomFeatureInsertMap = new TreeMap<>();
                    roomFeatureInsertMap.put("room_id", String.valueOf(selectedRoomId));
                    roomFeatureInsertMap.put("feature_id", featureId);
                    System.out.println("Inserting room_id: " + selectedRoomId + " feature_id: " + featureId);
                    roomFeatureModel.insertRoomFeature(roomFeatureInsertMap);
                }

                roomFeatureModel.selectAllRoomFeatures(roomFeatureColumns);
                roomFeatureTableView.setItems(roomFeatureModel.getRoomFeatures());



            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("On click Delete");
                var selectedFeatures = roomFeatureTableView.getSelectionModel().getSelectedItems();
                if (selectedFeatures.size() == 0) {
                    ViewUtils.showAlert("No feature item selected!");
                    return;
                }

                System.out.println("selected: " + selectedFeatures);
                for (RoomFeature roomFeature : selectedFeatures) {
                    var selectedFeatureId = roomFeature.getId();
                    System.out.println("Deleting id " + selectedFeatureId);
                    roomFeatureModel.deleteRoomFeature(selectedFeatureId);
                }

                roomFeatureModel.selectAllRoomFeatures(roomFeatureColumns);
                roomFeatureTableView.setItems(roomFeatureModel.getRoomFeatures());

            }
        });





    }
}
