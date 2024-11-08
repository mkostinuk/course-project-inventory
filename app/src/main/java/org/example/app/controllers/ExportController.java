package org.example.app.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.app.model.Product;
import org.example.app.services.ExportService;

import java.util.List;

public class ExportController {
    @FXML
    private Button backButton;
    @FXML
    private TableColumn<Product, String> titleColumn;
    @FXML
    private TableColumn<Product, String> quantityColumn;
    @FXML
    private TableColumn<Product, String> categoryColumn;
    @FXML
    private TableColumn<Product, String> priceColumn;
    @FXML
    private Button sendButton;
    @FXML
    private Button addButton;
    @FXML
    private Button clearButton;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TextField supplierField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField sumField;
    private final ExportService service = ExportService.getInstance();
    private final SceneController sceneController = SceneController.getInstance();
    private static final ObservableList<Product> products = FXCollections.observableArrayList();
    public void initialize() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        productTable.setItems(products);
        clearButton.setOnAction(event -> {
            products.clear();
            productTable.setItems(products);
        });
        sumField.setText("sum : " + products.stream().mapToDouble(Product::getPrice).sum());
        sendButton.setOnAction(event -> {
            service.makeDeal(supplierField.getText(), datePicker.getValue(), products, products.stream().mapToDouble(Product::getPrice).sum());
            sceneController.switchToMainMenu(event);
        });
        backButton.setOnAction(sceneController::switchToMainMenu);
    }
    @FXML
    private void handleAddButton() {
        sceneController.alertAddExistProducts();
    }
    public static void addProduct(Product product){
       products.add(product);
    }
    public static List<Product> getProducts() {
        return products.stream().toList();
    }
}
