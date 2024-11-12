package org.example.app.services;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.example.app.controllers.MainMenuController;
import org.example.app.model.ImportProducts;
import org.example.app.model.Product;

import java.time.LocalDate;

public class ImportService extends OperationService{
    private static ImportService instance;
    private ImportService(){

    }
    public static ImportService getInstance(){
        if(instance == null){
            instance = new ImportService();
        }
        return instance;
    }

    public void makeDeal(String text, LocalDate value, ObservableList<Product> products, double sum) {
        if(!canBuyAllProducts(products) || isCartEmpty(products)){
            return;
        }
        ImportProducts importProducts = new ImportProducts(sum, products, value);
        importProducts.setSupplier(text);
        updateProductStock(products);
        MainMenuController.decreaseMoney(sum);
        ReportGenerator.writeImport(importProducts);

    }
    protected void updateProductStock(ObservableList<Product> products) {
        for (Product product : products) {
            Product existingProduct = repo.getByTitle(product.getTitle()).orElse(null);
            if (existingProduct != null) {
                existingProduct.setQuantity(existingProduct.getQuantity() + product.getQuantity());
                repo.update(existingProduct);
            } else {
                repo.save(product);
            }
        }
    }
    private boolean canBuyAllProducts(ObservableList<Product> products){
        if(products.stream().mapToDouble(Product::getTotalPrice).sum()>AccountService.loadBalance()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You dont have enough money to buy these products!");
            alert.showAndWait();
            return false;
        }
        return true;
    }


}
