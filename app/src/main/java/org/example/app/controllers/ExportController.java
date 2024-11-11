package org.example.app.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import org.example.app.model.Product;
import org.example.app.services.ExportService;

import java.time.LocalDate;

public class ExportController extends OperationController {
    private final ExportService service = ExportService.getInstance();

    @Override
    protected void alertAddScene(ActionEvent event) {
        sceneController.alertAddExistProducts();
    }

    @Override
    protected void makeDeal(String title, LocalDate date, ObservableList<Product> products, double sum) {
        service.makeDeal(title, date, products, sum);
        clearTable();
    }
}
