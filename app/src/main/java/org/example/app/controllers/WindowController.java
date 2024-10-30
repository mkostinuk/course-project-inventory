package org.example.app.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class WindowController {
    private final Stage primaryStage;

    public WindowController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    public void switchToAddProducts(){
        loadScene("addProducts.fxml", "Edit Menu");
    }
    public void switchToMainMenu(){
        loadScene("main.fxml", "Main Menu");
    }
    public void loadScene(String fxml, String title) {
        try {
            var resource = getClass().getResource(fxml);
            if (resource == null) {
                throw new IllegalArgumentException("FXML file not found: " + fxml);
            }
            Parent root = FXMLLoader.load(resource);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle(title);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load FXML file: " + fxml, e);
        }
    }

}
