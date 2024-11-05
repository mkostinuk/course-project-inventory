package org.example.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.app.model.Product;
import org.example.app.services.MainMenuService;

public class MainMenuController {
    @FXML
    public Button addExistProductButton;
    @FXML
    public Button buttonEditProduct;
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
    private final MainMenuService service = MainMenuService.getInstance();
    @FXML
    public void initialize() {
        productColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        addProductButton.setOnAction(event -> SceneController.getInstance().switchToAddProducts(event));
        addExistProductButton.setOnAction(event -> SceneController.getInstance().switchToAddExistProducts(event) );
        productTable.setItems(service.getProducts());
        buttonEditProduct.setOnAction(event -> SceneController.getInstance().switchToEditMenu(event));
    }


}
