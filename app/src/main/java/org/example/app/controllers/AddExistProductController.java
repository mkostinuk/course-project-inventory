package org.example.app.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.app.services.AddProductService;

import java.util.List;

public class AddExistProductController {
    @FXML
    private Button addButton;
    @FXML
    private Button cancel;
    @FXML
    private TextField quantityField;
    @FXML
    private ComboBox<String> productNameComboBox;
    private final AddProductService service = AddProductService.getInstance();
    private final SceneController sceneController = SceneController.getInstance();
    @FXML
    public void initialize() {
        List<String> allProducts = service.productTitles();
        List<String> availableProducts = allProducts.stream()
                .filter(productName -> ExportController.getProducts()
                        .stream()
                        .noneMatch(product -> product.getTitle().equals(productName)))
                .toList();
        productNameComboBox.setItems(FXCollections.observableArrayList(availableProducts));
        addButton.setOnAction(event -> {
            service.addToExportTable(event, productNameComboBox.getValue(), quantityField.getText());
            sceneController.closeAlertScene(event);
        });

    }


    }

