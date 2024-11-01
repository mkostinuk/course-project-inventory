package org.example.app.services;

import javafx.scene.control.Alert;
import org.apache.commons.lang3.StringUtils;
import org.example.app.ProductRepo;
import org.example.app.model.Product;
import org.example.app.model.ProductCategory;

import java.util.List;

public class AddProductService {
    private final ProductRepo repo = ProductRepo.getInstance();
    private static AddProductService instance;

    private AddProductService(){

    }
    public static AddProductService getInstance(){
        if(instance == null){
            instance = new AddProductService();
        }
        return instance;
    }

    public void saveNewProduct(String title, String quantity, String price, ProductCategory category) {
        if (areBlankFields(title, quantity, price, category) ||
                areNonNumericFields(quantity, price) ||
                areNegativeFields(quantity, price) ||
                areBlankFields(title, quantity, price, category)||
                repo.existByTitle(title)){
            return;
        }

        repo.addNewProduct(new Product(title, Integer.parseInt(quantity), Double.parseDouble(price), category));
    }
    public void saveExistProduct(String title, String quantity) {
        if (areBlankFields(title, quantity) ||
                isNotNumericField(quantity, "Quantity") ||
                isNegativeField(quantity, "Quantity")){
            return;
        }
        repo.addExistProduct(title, Integer.parseInt(quantity));
    }
    public List<String> productTitles(){
        return repo.getAll().stream().map(Product::getTitle).toList();
    }

    private boolean areBlankFields(String title, String quantity, String price, ProductCategory category) {
        if(StringUtils.isAnyBlank(title, quantity, price) || category == null){
            showErrorMessage("All fields are required.");
            return true;
        }
        return false;

    }
    private boolean areBlankFields(String title, String quantity) {
        if(StringUtils.isAnyBlank(title, quantity)){
            showErrorMessage("All fields are required.");
            return true;
        }
        return false;

    }
    private boolean areNonNumericFields(String quantity, String price) {
        if(!StringUtils.isNumeric(quantity) || !StringUtils.isNumeric(price)){
            showErrorMessage("Quantity and price must be numeric.");
            return true;
        }
        return false;
    }
    private boolean isNotNumericField(String field, String fieldName){
        if(!StringUtils.isNumeric(field)){
            showErrorMessage(fieldName + " must be numeric.");
            return true;
        }
        return false;
    }
    private boolean areNegativeFields(String quantity, String price) {
        if(Integer.parseInt(quantity) < 0 || Integer.parseInt(price) < 0){
            showErrorMessage("Quantity and price must be non-negative.");
            return true;
        }
        return false;
    }
    private boolean isNegativeField(String field, String fieldName){
        if(Integer.parseInt(field) < 0){
            showErrorMessage(fieldName + " must be non-negative.");
            return true;
        }
        return false;
    }

    private void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }
}
