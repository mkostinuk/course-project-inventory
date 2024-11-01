package org.example.app.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.app.ProductRepo;
import org.example.app.model.Product;


public class MainMenuService {
    private final ProductRepo repo = ProductRepo.getInstance();
    private static MainMenuService instance;
    private MainMenuService(){

    }
    public static MainMenuService getInstance(){
        if(instance == null){
            instance = new MainMenuService();
        }
        return instance;
    }

    public ObservableList<Product> getProducts(){
        return FXCollections.observableArrayList(repo.getAll());
    }
}
