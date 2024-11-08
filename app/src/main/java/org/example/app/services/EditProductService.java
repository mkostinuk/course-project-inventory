package org.example.app.services;

import javafx.scene.control.Alert;
import org.example.app.repository.ProductRepo;
import org.example.app.controllers.UpdateProductsController;

public class EditProductService {
    private final ProductRepo productRepo = ProductRepo.getInstance();
    private static EditProductService instance;
    private EditProductService(){

    }
    public static EditProductService getInstance(){
        if(instance == null){
            return new EditProductService();
        }
        return instance;
    }

    public void transferToUpdateController(String title) {
        if(!productRepo.existByTitle(title)){
        UpdateProductsController.productTitle = title;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Product is not found");
        alert.show();
    }

}
