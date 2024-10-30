package org.example.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.app.ProductRepo;
import org.example.app.model.Product;
import org.example.app.model.ProductCategory;


public  class AddProductController {
    @FXML private TextField titleField;
    @FXML private TextField quantityField;
    @FXML private TextField priceField;
    @FXML private ComboBox<ProductCategory> categoryComboBox;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;

    private final ProductRepo productRepo = new ProductRepo();

    @FXML
    public void initialize() {
        categoryComboBox.getItems().setAll(ProductCategory.values());
        saveButton.setOnAction(event -> saveProduct());
        cancelButton.setOnAction(event -> closeWindow());
    }

    private void saveProduct() {
        try {
            String title = titleField.getText().trim();
            int quantity = Integer.parseInt(quantityField.getText().trim());
            double price = Double.parseDouble(priceField.getText().trim());
            ProductCategory category = categoryComboBox.getValue();

            if (title.isEmpty() || category == null) {
                showError("Please fill in all fields.");
                return;
            }

            Product existingProduct = productRepo.getByTitle(title);
            if (existingProduct != null) {
                existingProduct.setQuantity(existingProduct.getQuantity() + quantity);
                existingProduct.setPrice(price);
                productRepo.update(existingProduct);
            } else {
                Product product = new Product(title, quantity, price, category);
                productRepo.addNewProduct(product);
            }
            closeWindow();
        } catch (NumberFormatException e) {
            showError("Invalid input for quantity or price.");
        }
    }

    private void closeWindow() {
        saveButton.getScene().getWindow().hide();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
}