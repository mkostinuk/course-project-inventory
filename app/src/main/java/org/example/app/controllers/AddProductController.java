package org.example.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.app.model.ProductCategory;
import org.example.app.services.AddProductService;


public  class AddProductController {
    @FXML
    private Button addButton;
    @FXML
    private TextField titleField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField priceField;
    @FXML
    private ComboBox<ProductCategory> categoryComboBox;
    @FXML
    private Button closeButton;
    private final AddProductService service = AddProductService.getInstance();
    private final SceneController sceneController = SceneController.getInstance();
    @FXML
    public void initialize() {
        categoryComboBox.getItems().setAll(ProductCategory.values());
        addButton.setOnAction(event -> {
            service.addToImportTable(titleField.getText(), quantityField.getText(), priceField.getText(), categoryComboBox.getValue());
            sceneController.closeAlertScene(event);
        });
        closeButton.setOnAction(sceneController::closeAlertScene);
    }


    }

