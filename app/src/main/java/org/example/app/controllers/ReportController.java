package org.example.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import org.example.app.services.ReportService;



public class ReportController {
    private static final String REPORT_PATH = "report.txt";
    private static final ReportService service = ReportService.getInstance();

    @FXML
    private void onGetReportButtonClicked(ActionEvent event) {
        service.copyFileToDirectory(REPORT_PATH, event);
    }
}
