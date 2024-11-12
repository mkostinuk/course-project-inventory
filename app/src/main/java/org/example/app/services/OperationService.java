package org.example.app.services;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.example.app.model.Product;
import org.example.app.repository.ProductRepo;

import java.time.LocalDate;

public abstract class OperationService {
    protected final ProductRepo repo = ProductRepo.getInstance();
    public abstract void makeDeal(String text, LocalDate value, ObservableList<Product> products, double sum);
    protected abstract void updateProductStock(ObservableList<Product> products);

    protected  boolean isCartEmpty(ObservableList<Product> products){
        if(products.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Cart is empty!");
            return true;
        }
        return false;
    }
}
