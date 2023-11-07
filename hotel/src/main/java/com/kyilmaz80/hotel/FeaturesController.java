package com.kyilmaz80.hotel;

import com.kyilmaz80.hotel.models.Features;
import com.kyilmaz80.hotel.models.FeaturesModel;
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

import java.net.URL;
import java.util.ResourceBundle;

public class FeaturesController extends SceneController implements Initializable {

    @FXML
    private TableView<Features> featuresTableView;
    @FXML
    private TableColumn<Features, Integer> featuresId;

    @FXML
    private TableColumn<Features, String> featuresName;

    @FXML
    private Button filterButton;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField featureNameTextField;

    private FeaturesModel model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //super.initialize(location, resources);
        /*
        featuresId.setCellValueFactory(new PropertyValueFactory<Features, Integer>("id"));
        featuresName.setCellValueFactory((new PropertyValueFactory<Features, String>("name")));
        model = new FeaturesModel();

        model.updateFeatureList("");

        featuresTableView.setItems(model.getFeatures());
         */

        filterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("On click Filter");
                model.selectFeatureList(featureNameTextField.getText());
                featuresTableView.setItems(model.getFeatures());
            }
        });

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String featureName = featureNameTextField.getText();
                System.out.println("On click Add");
                if (!StringUtils.inputValid1(featureName)) {
                    System.out.println("No feature!");
                    return;
                }
                model.insertFeature(featureName);
                model.selectFeatureList("");
                featuresTableView.setItems(model.getFeatures());
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("On click Delete");
                var selected = featuresTableView.getSelectionModel().getSelectedItem();
                if (selected == null) {
                    ViewUtils.showAlert("No item selected!");
                    return;
                }
                System.out.println("selected: " + selected);
                var selectedId = selected.getId();
                System.out.println("Deleting id " + selectedId);

                model.deleteFeature(selectedId);
                model.selectFeatureList("");
                featuresTableView.setItems(model.getFeatures());
            }
        });


        featuresId.setCellValueFactory(new PropertyValueFactory<Features, Integer>("id"));
        featuresName.setCellValueFactory((new PropertyValueFactory<Features, String>("name")));
        model = new FeaturesModel();

        model.selectFeatureList("");
        featuresTableView.setItems(model.getFeatures());

    }

    /*
    @FXML
    void onClickAdd(ActionEvent event) throws IOException {
        //System.out.println("On click Add");
        model.insertFeature(featureNameTextField.getText());
        model.updateFeatureList(featureNameTextField.getText());
        featuresTableView.setItems(model.getFeatures());
    }

    @FXML
    void onClickFilter(ActionEvent event) throws IOException {
        //System.out.println("On click Add");
        model.updateFeatureList(featureNameTextField.getText());
        featuresTableView.setItems(model.getFeatures());
    }

    @FXML
    void onClickDelete(ActionEvent event) throws IOException {
        System.out.println("On click delete");
        System.out.println("Deleting id " + featuresTableView.getSelectionModel().getSelectedItem().getId());
        model.deleteFeature(featuresTableView.getSelectionModel().getSelectedItem().getId());
        featuresTableView.setItems(model.getFeatures());
    }

     */
}
