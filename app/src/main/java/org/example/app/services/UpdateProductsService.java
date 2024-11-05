package org.example.app.services;

import javafx.scene.control.Alert;
import org.example.app.ProductRepo;
import org.example.app.model.Product;

public class UpdateProductsService {
    private final ProductRepo repo;
    public static  UpdateProductsService instance;


    private UpdateProductsService() {
        this.repo = ProductRepo.getInstance();
    }
    public static UpdateProductsService getInstance(){
        if(instance == null){
            return new UpdateProductsService();
        }
        return instance;
    }

    public Product getProduct(String title){
        try {
            return repo.getByTitle(title).orElseThrow(RuntimeException::new);
        }
        catch (RuntimeException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Product not found.");
            alert.show();
            return null;
        }

    }

    public void updateProduct(String productTitle) {
        repo.update(getProduct(productTitle));
    }
}