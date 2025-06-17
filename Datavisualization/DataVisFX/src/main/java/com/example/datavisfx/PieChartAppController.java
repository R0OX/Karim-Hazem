package com.example.datavisfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class PieChartAppController {

    @FXML
    private TextField itemField;

    @FXML
    private TextField amountField;

    @FXML
    private TextField chartNameField;

    @FXML
    private Button addButton;

    @FXML
    private Button createChartButton;

    @FXML
    private Button resetButton;

    @FXML
    private Button downloadButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<DataEntry> tableView;

    @FXML
    private TableColumn<DataEntry, String> nameColumn;

    @FXML
    private TableColumn<DataEntry, Double> valueColumn;

    @FXML
    private PieChart pieChart;

    private final ObservableList<DataEntry> dataEntries = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());
        valueColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());
        tableView.setItems(dataEntries);
    }

    @FXML
    private void addDataEntry(ActionEvent event) {
        String item = itemField.getText();
        String amountText = amountField.getText();

        if (item.isEmpty() || amountText.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Item and amount must not be empty.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);
            dataEntries.add(new DataEntry(item, amount));
            itemField.clear();
            amountField.clear();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Amount must be a valid number.");
        }
    }

    @FXML
    private void createPieChart(ActionEvent event) {
        pieChart.getData().clear();
        String chartName = chartNameField.getText();
        pieChart.setTitle(chartName);

        for (DataEntry entry : dataEntries) {
            PieChart.Data slice = new PieChart.Data(entry.getItemName() + " (" + entry.getAmount() + ")", entry.getAmount());
            pieChart.getData().add(slice);
        }
    }

    @FXML
    private void resetData(ActionEvent event) {
        dataEntries.clear();
        pieChart.getData().clear();
        itemField.clear();
        amountField.clear();
        chartNameField.clear();
    }

    @FXML
    private void downloadChart(ActionEvent event) {
        WritableImage image = pieChart.snapshot(null, null);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Chart Image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Files", "*.png"));
        File file = fileChooser.showSaveDialog(pieChart.getScene().getWindow());

        if (file != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            } catch (IOException e) {
                showAlert(Alert.AlertType.ERROR, "Save Error", "Could not save image.");
            }
        }
    }

    @FXML
    private void deleteLastEntry(ActionEvent event) {
        if (!dataEntries.isEmpty()) {
            dataEntries.remove(dataEntries.size() - 1);
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            // Load the Home.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Get the current stage and set the new scene (home scene)
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
