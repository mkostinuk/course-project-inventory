package org.example.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EditMenuController {
    @FXML
    public TextField setUpTextField;
    @FXML
    public Button backButton;
    @FXML
    private Button buttonSetUp;

    @FXML
    public void initialize() {
        buttonSetUp.setOnAction(this::setUp);
        backButton.setOnAction(SceneController.getInstance()::switchToMainMenu);
    }

    public void setUp(ActionEvent event) {
        UpdateProductsController.productTitle = setUpTextField.getText();
        SceneController.getInstance().alertChangeProducts(event);
    }
}
