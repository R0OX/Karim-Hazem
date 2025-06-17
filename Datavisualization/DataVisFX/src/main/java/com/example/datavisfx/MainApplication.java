package com.example.datavisfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.InputStream;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("DataVis Home");

        InputStream iconStream = getClass().getResourceAsStream("/Logo.png"); // Adjust path if necessary
        if (iconStream == null) {
            System.out.println("Image not found! Please check the path.");
        } else {
            Image icon = new Image(iconStream);
            stage.getIcons().add(icon);
        }

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
