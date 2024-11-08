package org.example.app.services;

import org.example.app.model.ExportProduct;
import org.example.app.model.Product;
import org.example.app.repository.ProductRepo;

import java.time.LocalDate;
import java.util.List;

public class ExportService {
    private static ExportService instance;
    private ProductRepo productRepo = ProductRepo.getInstance();

    private ExportService(){

    }
    public static ExportService getInstance(){
        if(instance == null){
            instance = new ExportService();
        }
        return instance;
    }
    public void makeDeal(String customer, LocalDate date, List<Product> products, double sum) {
        ExportProduct exportProduct = new ExportProduct(sum, products, date);
        exportProduct.setCustomer(customer);
        updateProductStock(products);
        ReportGenerator.writeExport(exportProduct);
    }
    private void updateProductStock(List<Product> soldProducts) {
        for (Product soldProduct : soldProducts) {
            Product productInStock = productRepo.getByTitle(soldProduct.getTitle()).orElse(null);

            if (productInStock != null) {
                int remainingQuantity = productInStock.getQuantity() - soldProduct.getQuantity();

                if (remainingQuantity > 0) {
                    productInStock.setQuantity(remainingQuantity);
                    productRepo.update(productInStock);
                } else {
                    productRepo.delete(productInStock);
                }
            }
        }
    }
}
