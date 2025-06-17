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
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class BarChartAppController {
    @FXML
    private TextField itemField;
    @FXML
    private TextField amountField;
    @FXML
    private TextField chartNameField;
    @FXML
    private TableView<DataEntry> tableView;
    @FXML
    private TableColumn<DataEntry, String> nameColumn;
    @FXML
    private TableColumn<DataEntry, Double> valueColumn;
    @FXML
    private BarChart<String, Number> barChart;

    private final ObservableList<DataEntry> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        tableView.setItems(data);
    }

    @FXML
    private void addDataEntry(ActionEvent event) {
        String item = itemField.getText();
        double value;
        try {
            value = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
            return;
        }

        if (!item.isEmpty()) {
            data.add(new DataEntry(item, value));
            itemField.clear();
            amountField.clear();
        }
    }

    @FXML
    private void createBarChart(ActionEvent event) {
        barChart.getData().clear();
        String chartName = chartNameField.getText();
        barChart.setTitle(chartName);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (DataEntry entry : data) {
            series.getData().add(new XYChart.Data<>(entry.getName(), entry.getValue()));
        }
        barChart.getData().add(series);
    }

    @FXML
    private void deleteLastEntry(ActionEvent event) {
        if (!data.isEmpty()) {
            data.remove(data.size() - 1);
        }
    }

    @FXML
    private void resetData(ActionEvent event) {
        data.clear();
        barChart.getData().clear();
        chartNameField.clear();
    }

    @FXML
    private void downloadChart(ActionEvent event) {
        WritableImage image = barChart.snapshot(new SnapshotParameters(), null);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("barchart.png");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png"));
        File file = fileChooser.showSaveDialog(barChart.getScene().getWindow());

        if (file != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
                System.out.println("Chart saved to " + file.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Failed to save image: " + e.getMessage());
            }
        }
    }

    public static class DataEntry {
        private final String name;
        private final Double value;

        public DataEntry(String name, Double value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public Double getValue() {
            return value;
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
