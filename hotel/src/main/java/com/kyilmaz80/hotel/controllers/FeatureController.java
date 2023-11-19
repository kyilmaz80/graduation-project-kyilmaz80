package com.kyilmaz80.hotel.controllers;

import com.kyilmaz80.hotel.ViewUtils;
import com.kyilmaz80.hotel.models.Feature;
import com.kyilmaz80.hotel.models.FeatureModel;
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

public class FeatureController extends SceneController implements Initializable {

    @FXML
    private TableView<Feature> featureTableView;
    @FXML
    private TableColumn<Feature, Integer> featureId;

    @FXML
    private TableColumn<Feature, String> featureName;

    @FXML
    private TableColumn<Feature, Double> featurePrice;

    @FXML
    private Button filterButton;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField featureNameTextField;

    @FXML
    private TextField featurePriceTextField;

    private FeatureModel model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        featureId.setCellValueFactory(new PropertyValueFactory<Feature, Integer>("id"));
        featureName.setCellValueFactory((new PropertyValueFactory<Feature, String>("name")));
        featurePrice.setCellValueFactory((new PropertyValueFactory<Feature, Double>("price")));

        model = new FeatureModel();

        model.selectAllFeatures();
        featureTableView.setItems(model.getFeatures());

        filterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("On click Filter");
                String featureName = featureNameTextField.getText();
                String featurePrice = featurePriceTextField.getText();
                if (!StringUtils.inputValid1(featureName)) {
                    System.err.println("FeatureName Input not valid!");
                    return;
                }

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

                featureTableView.setItems(model.getFeatures());
            }
        });

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String featureName = featureNameTextField.getText();
                String featurePrice = featurePriceTextField.getText();
                System.out.println("On click Add");
                if (!StringUtils.inputValid1(featureName)) {
                    System.err.println("FeatureName Input not valid!");
                    return;
                }

                if (!StringUtils.inputValid2(featurePrice)) {
                    System.err.println("FeaturePrice Input not valid!");
                    return;
                }

                if (!featurePrice.isEmpty()) {
                    model.insertFeature(featureName, Double.parseDouble(featurePrice));
                } else {
                    model.insertFeature(featureName);
                }
                model.selectAllFeatures();
                featureTableView.setItems(model.getFeatures());
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("On click Delete");
                var selected = featureTableView.getSelectionModel().getSelectedItem();
                if (selected == null) {
                    ViewUtils.showAlert("No item selected!");
                    return;
                }
                System.out.println("selected: " + selected);
                var selectedId = selected.getId();
                System.out.println("Deleting id " + selectedId);

                model.deleteFeature(selectedId);
                model.selectAllFeatures();
                featureTableView.setItems(model.getFeatures());
            }
        });

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
