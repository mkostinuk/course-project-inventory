package org.example.app.services;

import javafx.collections.ObservableList;
import org.example.app.controllers.MainMenuController;
import org.example.app.model.ExportProduct;
import org.example.app.model.Product;

import java.time.LocalDate;

public class ExportService extends OperationService {
    private static ExportService instance;

    private ExportService(){

    }
    public static ExportService getInstance(){
        if(instance == null){
            instance = new ExportService();
        }
        return instance;
    }
    @Override
    public void makeDeal(String customer, LocalDate date, ObservableList<Product> products, double sum) {
        if(isCartEmpty(products)){
            return;
        }
        ExportProduct exportProduct = new ExportProduct(sum, products, date);
        exportProduct.setCustomer(customer);
        updateProductStock(products);
        MainMenuController.increaseMoney(sum);
        ReportGenerator.writeExport(exportProduct);
    }
    @Override
    protected void updateProductStock(ObservableList<Product> soldProducts) {
        for (Product soldProduct : soldProducts) {
            Product productInStock = repo.getByTitle(soldProduct.getTitle()).orElse(null);
            if (productInStock != null) {
                int remainingQuantity = productInStock.getQuantity() - soldProduct.getQuantity();

                if (remainingQuantity > 0) {
                    productInStock.setQuantity(remainingQuantity);
                    repo.update(productInStock);
                } else {
                   repo.delete(productInStock);
                }
            }
        }
    }


}
