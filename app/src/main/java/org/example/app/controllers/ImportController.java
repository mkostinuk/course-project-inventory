package org.example.app.controllers;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import org.example.app.model.Product;
import org.example.app.services.ImportService;

import java.time.LocalDate;

public class ImportController extends OperationController {
    private final ImportService service = ImportService.getInstance();


    @Override
    protected void alertAddScene(ActionEvent event) {
        sceneController.alertAddProducts();
    }

    @Override
    protected void makeDeal(String text, LocalDate date, ObservableList<Product> products, double sum) {
        service.makeDeal(text, date, products, sum);
        clearTable();
    }
}
