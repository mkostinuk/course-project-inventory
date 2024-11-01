package org.example.app.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.app.services.AddProductService;

public class AddExistProductController {
    @FXML
    public TextField quantityField;
    @FXML
    public ComboBox<String> productNameComboBox;
    @FXML
    public Button backButton;
    @FXML
    public Button saveButton;
    private final AddProductService service = AddProductService.getInstance();
    private final SceneController sceneController = SceneController.getInstance();
    @FXML
    public void initialize() {
        productNameComboBox.setItems(FXCollections.observableArrayList(service.productTitles()));
        saveButton.setOnAction(this::saveExist);
        backButton.setOnAction(sceneController::switchToMainMenu);
    }
    public void saveExist(ActionEvent event){
        service.saveExistProduct(
                productNameComboBox.getValue(),
                quantityField.getText()
        );
        sceneController.switchToMainMenu(event);

    }
}
