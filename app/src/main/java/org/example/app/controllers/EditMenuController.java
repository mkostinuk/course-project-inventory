package org.example.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.app.services.EditProductService;

public class EditMenuController {
    @FXML
    private TextField setUpTextField;
    @FXML
    private Button backButton;
    @FXML
    private Button buttonSetUp;
    private final EditProductService editProductService = EditProductService.getInstance();

    @FXML
    public void initialize() {
        buttonSetUp.setOnAction(this::setUp);
        backButton.setOnAction(SceneController.getInstance()::switchToMainMenu);
    }

    private void setUp(ActionEvent event) {
        editProductService.transferToUpdateController(setUpTextField.getText());
        SceneController.getInstance().alertChangeProducts();
    }
}
