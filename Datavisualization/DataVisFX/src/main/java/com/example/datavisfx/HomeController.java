package com.example.datavisfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    private void goToLineChart(ActionEvent event) {
        navigateToScene(event, "LineChartApp.fxml");
    }

    @FXML
    private void goToPieChart(ActionEvent event) {
        navigateToScene(event, "PieChartApp.fxml");
    }

    @FXML
    private void goToVerticalBarChart(ActionEvent event) {
        navigateToScene(event, "BarChartApp.fxml");
    }

    private void navigateToScene(ActionEvent event, String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Get the current stage and set the new scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
