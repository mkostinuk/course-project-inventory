package org.example.app.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;
import org.example.app.model.Product;
import org.example.app.model.ProductCategory;
import org.example.app.services.UpdateProductsService;



public class UpdateProductsController {
    @FXML
    private TableColumn<Product,Integer> quantityColumn;
    @FXML
    private TextField titleField;
    @FXML
    private TextField priceField;
    @FXML
    private Button backButton;
    @FXML
    private Button backToMainButton;
    @FXML
    private Button updateButton;
    @FXML
    private ComboBox<ProductCategory> categoryComboBox;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, String> titleColumn;
    @FXML
    private TableColumn<Product, Integer> priceColumn;
    @FXML
    private TableColumn<Product, String> categoryColumn;
    @Setter
    private static String productTitle;
    private final UpdateProductsService service = UpdateProductsService.getInstance();
    private final SceneController sceneController = SceneController.getInstance();
    @FXML
    public void initialize() {
        categoryComboBox.setItems(FXCollections.observableArrayList(ProductCategory.values()));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        productTable.setItems(FXCollections.observableArrayList(service.getProduct(productTitle)));
        backButton.setOnAction(event ->{
            sceneController.closeWindow();
            sceneController.switchToEditMenu(event);
        });
        backToMainButton.setOnAction(event -> {
            sceneController.closeWindow();
            sceneController.switchToMainMenu(event);

        });
        updateButton.setOnAction(event -> {
            service.updateProduct(productTitle, titleField.getText(), Integer.parseInt(priceField.getText()));
            sceneController.closeWindow();
            sceneController.switchToMainMenu(event);

        });
    }


}
