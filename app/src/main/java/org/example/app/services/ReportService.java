package org.example.app.services;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.example.app.controllers.SceneController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ReportService {
    private final SceneController sceneController = SceneController.getInstance();

    private static ReportService instance;
    private ReportService(){

    }
    public static ReportService getInstance(){
        if(instance == null){
            return new ReportService();
        }
        return instance;
    }
    public void copyFileToDirectory(String filepath, ActionEvent event){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose the directory to download report");
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        File selectedDirectory = directoryChooser.showDialog(stage);
        if (selectedDirectory != null) {
            Path destinationPath = Path.of(selectedDirectory.getAbsolutePath(), "report.txt");
            try {
                Files.copy(Path.of(filepath), destinationPath, StandardCopyOption.REPLACE_EXISTING);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Report was copied to " + selectedDirectory.getAbsolutePath());
                alert.showAndWait();
                sceneController.closeAlertScene(event);
                sceneController.switchToMainMenu(event);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error in copying file");
                alert.showAndWait();
                sceneController.closeAlertScene(event);
                sceneController.switchToMainMenu(event);

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Directory isn`t chosen");
            alert.showAndWait();
        }
    }

}
