package com.example.datavisfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class LineChartAppController {

    @FXML
    private TextField xValueField;
    @FXML
    private TextField yValueField;
    @FXML
    private TextField chartTitleField;
    @FXML
    private Button addButton;
    @FXML
    private Button createChartButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button downloadButton;
    @FXML
    private Button deleteButton;  // Button to delete the last entry
    @FXML
    private TableView<XYChart.Data<Number, Number>> tableView;
    @FXML
    private TableColumn<XYChart.Data<Number, Number>, Number> xColumn;
    @FXML
    private TableColumn<XYChart.Data<Number, Number>, Number> yColumn;
    @FXML
    private LineChart<Number, Number> lineChart;

    private ObservableList<XYChart.Data<Number, Number>> dataPoints;

    public void initialize() {
        dataPoints = FXCollections.observableArrayList();
        xColumn.setCellValueFactory(cellData -> cellData.getValue().XValueProperty());
        yColumn.setCellValueFactory(cellData -> cellData.getValue().YValueProperty());
        tableView.setItems(dataPoints);
    }

    @FXML
    private void addDataPoint() {
        try {
            Number xValue = Double.parseDouble(xValueField.getText());
            Number yValue = Double.parseDouble(yValueField.getText());
            dataPoints.add(new XYChart.Data<>(xValue, yValue));
            xValueField.clear();
            yValueField.clear();
            createLineChart(); // Refresh the chart after adding new data
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for X or Y values.");
        }
    }

    @FXML
    private void createLineChart() {
        lineChart.getData().clear();
        XYChart.Series<Number, Number> series = new XYChart.Series<>(dataPoints);
        series.setName(chartTitleField.getText());
        lineChart.getData().add(series);
    }

    @FXML
    private void resetChart() {
        dataPoints.clear();
        lineChart.getData().clear();
        chartTitleField.clear();
    }

    @FXML
    private void deleteLastEntry() {
        if (!dataPoints.isEmpty()) {
            dataPoints.remove(dataPoints.size() - 1); // Remove the last entry
            createLineChart(); // Refresh the chart
        }
    }

    @FXML
    private void downloadChart() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Chart as PNG");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Files", "*.png"));
        File file = fileChooser.showSaveDialog(downloadButton.getScene().getWindow());

        if (file != null) {
            try {
                javafx.scene.image.WritableImage snapshot = lineChart.snapshot(null, null);
                javax.imageio.ImageIO.write(javafx.embed.swing.SwingFXUtils.fromFXImage(snapshot, null), "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
