package org.example.app.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.app.model.Product;

import java.time.LocalDate;
import java.util.List;

public abstract class OperationController {
    @FXML
    public Button addButton;
    @FXML
    public Button clearButton;
    @FXML
    private Button sendButton;
    @FXML
    private Button backButton;
    @FXML
    private Label sumLabel;
    @FXML
    private TableColumn<Product, String> titleColumn;
    @FXML
    private TableColumn<Product, String> categoryColumn;
    @FXML
    private TableColumn<Product, String> quantityColumn;
    @FXML
    private TableColumn<Product, String> priceColumn;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TextField supplierField;
    @FXML
    public DatePicker datePicker;
    private static final ObservableList<Product> products = FXCollections.observableArrayList();
    protected final SceneController sceneController = SceneController.getInstance();
    @FXML
    private void initialize(){
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        productTable.setItems(products);
        clearButton.setOnAction(event -> clearTable());
        sendButton.setOnAction(event -> {
            makeDeal(supplierField.getText() ,datePicker.getValue(), products, products.stream().mapToDouble(Product::getTotalPrice).sum());
            sceneController.switchToMainMenu(event);
        });
        backButton.setOnAction(sceneController::switchToMainMenu);
        addButton.setOnAction(this::alertAddScene);
        updateSum();
    }
    protected void updateSum() {
        double sum = products.stream().mapToDouble(Product::getPrice).sum();
        sumLabel.setText("sum : " + sum);
    }
    protected void clearTable(){
        products.clear();
        productTable.setItems(products);
    }
    protected abstract void alertAddScene(ActionEvent event);

    protected abstract void makeDeal(String title,  LocalDate date, ObservableList<Product> products, double sum);

    public static void addProduct(Product product){
        products.add(product);

    }

    public static List<Product> getProducts() {
        return products.stream().toList();
    }
}
