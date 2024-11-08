package org.example.app.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.app.model.Product;
import org.example.app.model.ProductCategory;
import org.example.app.services.UpdateProductsService;



public class UpdateProductsController {
    @FXML
    private TextField fieldTitle;
    @FXML
    private TextField fieldPrice;
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
    private TableColumn<Product, String> columnTitle;
    @FXML
    private TableColumn<Product, Integer> columnPrice;
    @FXML
    private TableColumn<Product, ProductCategory> columnCategory;
    public static String productTitle;
    private final UpdateProductsService service = UpdateProductsService.getInstance();
    @FXML
    public void initialize() {
        categoryComboBox.setItems(FXCollections.observableArrayList(ProductCategory.values()));
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        productTable.setItems(FXCollections.observableArrayList(service.getProduct(productTitle)));
        backButton.setOnAction(event ->{
            SceneController.getInstance().closeWindow();
            SceneController.getInstance().switchToEditMenu(event);

        });
        backToMainButton.setOnAction(event -> {
            SceneController.getInstance().closeWindow();
            SceneController.getInstance().switchToMainMenu(event);

        });
        updateButton.setOnAction(event -> service.updateProduct(productTitle, fieldTitle.getText(), Integer.parseInt(fieldPrice.getText())));
    }




}
