package org.example.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.app.model.ProductCategory;
import org.example.app.services.AddProductService;


public  class AddProductController {
    @FXML private TextField titleField;
    @FXML private TextField quantityField;
    @FXML private TextField priceField;
    @FXML private ComboBox<ProductCategory> categoryComboBox;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;
    @FXML private Button backButton;
    private final AddProductService addProductService = AddProductService.getInstance();
    private final SceneController sceneController = SceneController.getInstance();
    @FXML
    public void initialize() {
        categoryComboBox.getItems().setAll(ProductCategory.values());
        saveButton.setOnAction(this::saveProduct);
        cancelButton.setOnAction(event -> sceneController.closeWindow());
        backButton.setOnAction(sceneController::switchToMainMenu);
    }

    private void saveProduct(ActionEvent event) {
        addProductService.saveNewProduct(
                titleField.getText(),
                quantityField.getText(),
                priceField.getText(),
                categoryComboBox.getValue()
        );
        sceneController.switchToMainMenu(event);

    }

}