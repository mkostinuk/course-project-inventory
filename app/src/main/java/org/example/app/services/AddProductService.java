package org.example.app.services;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import org.apache.commons.lang3.StringUtils;
import org.example.app.controllers.ImportController;
import org.example.app.repository.ProductRepo;
import org.example.app.controllers.ExportController;
import org.example.app.model.Product;
import org.example.app.model.ProductCategory;

import java.util.List;

public class AddProductService {
    private final ProductRepo repo;
    private static AddProductService instance;

    private AddProductService(){
        repo = ProductRepo.getInstance();
    }
    public static AddProductService getInstance(){
        if(instance == null){
            instance = new AddProductService();
        }
        return instance;
    }

    public void saveNewProduct(String title, String quantity, String price, ProductCategory category) {
        if (
                areBlankFields(title, quantity, price, category) ||
                areNonNumericFields(quantity, price) ||
                areNegativeFields(quantity, price) ||
                existByTitle(title)
        ){
                return;
        }
        repo.save(new Product(
                title.toLowerCase(),
                Integer.parseInt(quantity),
                Double.parseDouble(price),
                category
        ));
    }
    public void saveExistProduct(String title, String quantity) {
        if (
                doesNotExistByTitle(title) ||
                areBlankFields(title, quantity) ||
                isNotNumericField(quantity, "Quantity") ||
                isNegativeField(quantity, "Quantity")
        ){
                return;
        }
        repo.saveExist(title, Integer.parseInt(quantity));
    }
    public List<String> productTitles(){
        if(repo.getAll().isEmpty()){
            showErrorMessage("No products found.");
            return null;
        }
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
    private boolean existByTitle(String title){
        if(repo.existByTitle(title)){
            showErrorMessage("Product already exists.");
            return true;
        }
        return false;
    }
   private boolean doesNotExistByTitle(String title){
        if(!repo.existByTitle(title)){
            showErrorMessage("Product does not exist.");
            return true;
        }
        return false;
    }
    private Product getProductByTitle(String title){
        if(!repo.existByTitle(title)){
            showErrorMessage("Product does not exist.");
            return null;
        }
        return repo.getByTitle(title).orElseThrow();
    }
    public void addToTable(ActionEvent event, String productTitle, String quantity){
        if(
                isNotNumericField(quantity, "Quantity") ||
                isNegativeField(quantity, "Quantity")
        ){
            return;
        }
        Product product = getProductByTitle(productTitle);
        if(product == null){
            return;
        }
        product.setQuantity(Integer.parseInt(quantity));
        ExportController.addProduct(product); //FIXME

    }
    public void addToExportTable(ActionEvent event, String productTitle, String quantity){
        if(
                isNotNumericField(quantity, "Quantity") ||
                isNegativeField(quantity, "Quantity")
        ){
            return;
        }
        Product product = getProductByTitle(productTitle);
        int numericQuantity = Integer.parseInt(quantity);
        assert product != null;
        if(isToBigQuantity(product, numericQuantity)){
            return;
        }
        product.setQuantity(numericQuantity);
        ExportController.addProduct(product);


    }

    private boolean isToBigQuantity(Product product, int quantity) {
        if(product.getQuantity() < quantity){
            showErrorMessage("Quantity is too big.");
            return true;
        }
        return false;
    }

    public void addToImportTable(String productTitle, String quantity, String price, ProductCategory category) {
        if(
                areBlankFields(productTitle, quantity, price, category) ||
                areNonNumericFields(quantity, price) ||
                areNegativeFields(quantity, price)
        ){
            return;
        }
            Product product = new Product(
                    productTitle.toLowerCase(),
                    Integer.parseInt(quantity),
                    Double.parseDouble(price),
                    category
            );
        if(AccountService.loadBalance() < product.getTotalPrice()){
            showErrorMessage("Not enough money.");
            return;
        }
        ImportController.addProduct(product);
    }
}
