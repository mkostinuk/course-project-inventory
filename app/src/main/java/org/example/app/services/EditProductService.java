package org.example.app.services;

import javafx.scene.control.Alert;
import org.example.app.controllers.SceneController;
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

    public void transferToUpdateControllerAndChangeScene(String title) {
        if(!productRepo.existByTitle(title)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Product is not found");
            alert.showAndWait();
            return;
        }
        UpdateProductsController.setProductTitle(title);
        SceneController.getInstance().alertChangeProducts();

    }

}
