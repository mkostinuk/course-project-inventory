package org.example.app.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.Setter;
import lombok.SneakyThrows;
import org.example.app.model.Product;

import java.util.Objects;

public class MainMenuController {
    @Setter
    private WindowController windowController;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, String> productColumn;
    @FXML
    private TableColumn<Product, Integer> quantityColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;
    @FXML
    private TableColumn<Product, String> categoryColumn;

    @FXML
    private Button addProductButton;
    @FXML
    private Button viewInventoryButton;
    @FXML
    private Button backButton;

    @FXML
    public void initialize() {
        productColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        productTable.setItems(getProductData());
    }


    @FXML
    private void handleAddProduct() {
        windowController.switchToAddProducts();
    }
    @FXML
    private void handleViewInventory() {
        //todo
        productTable.setItems(getProductData());
    }
    private ObservableList<Product> getProductData() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        //todo
        return products;
    }


}
