package org.example.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.app.model.Product;
import org.example.app.services.AccountService;
import org.example.app.services.MainMenuService;

public class MainMenuController {
    @FXML
    public Label accountLabel;
    @FXML
    private Button addGoodsButton;
    @FXML
    private Button reportButton;
    @FXML
    private Button sendGoodsButton;
    @FXML
    private Button editProductButton;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, String> titleColumn;
    @FXML
    private TableColumn<Product, Integer> quantityColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;
    @FXML
    private TableColumn<Product, String> categoryColumn;
    private static double money = AccountService.loadBalance();

    private final MainMenuService service = MainMenuService.getInstance();
    private final SceneController sceneController = SceneController.getInstance();
    @FXML
    public void initialize() {
        double productMoney = service.allProductMoney();
        addGoodsButton.setOnAction(sceneController::switchToImportMenu);
        reportButton.setOnAction(sceneController::alertReportMenu);
        sendGoodsButton.setOnAction(sceneController::switchToExportMenu);
        editProductButton.setOnAction(sceneController::switchToEditMenu);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        productTable.setItems(service.getProducts());
        accountLabel.setText(String.format("Money: \n %,.2f \n Product Money: \n %,.2f ", money, productMoney));
    }
    public static void increaseMoney(double money){
        MainMenuController.money += money;
        AccountService.saveBalance(MainMenuController.money);
    }
    public static void decreaseMoney(double money){
        MainMenuController.money -= money;
        AccountService.saveBalance(MainMenuController.money);
    }

}
