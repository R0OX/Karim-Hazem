package com.example.datavisfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BarChartApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("BarChartApp.fxml"));
        stage.setTitle("Vertical Bar Chart App");
        stage.setScene(new Scene(root, 1010, 600));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}