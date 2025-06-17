package com.example.datavisfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PieChartApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("PieChartApp.fxml"));
        primaryStage.setTitle("Pie Chart Application");
        primaryStage.setScene(new Scene(root, 1010, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
