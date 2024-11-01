package org.example.app.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.example.app.Main;

import java.io.IOException;


public class SceneController {
    private Stage stage;
    private Parent root;
    private Scene scene;
    private static SceneController instance;

    public static SceneController getInstance() {
        if(instance == null) {
            instance = new SceneController();
        }
        return instance;
    }


    public void switchToAddProducts(ActionEvent event)  {
        loadScene("addProducts.fxml", "Add Product", event);
    }
    public void switchToMainMenu(ActionEvent event) {
        loadScene("main.fxml", "Main Menu", event);
    }

    private SceneController(){}


    public void loadScene(String fxml, String title, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
            root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load FXML file: " + fxml, e);
        }
    }
    public void closeWindow() {
        stage = (Stage) stage.getScene().getWindow();
        stage.close();
    }

    public void switchToAddExistProducts(ActionEvent event) {
        loadScene("addExistProduct.fxml", "Add Exist Product", event);
    }
}
