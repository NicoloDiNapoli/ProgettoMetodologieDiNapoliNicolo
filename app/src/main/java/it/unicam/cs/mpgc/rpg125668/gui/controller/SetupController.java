package it.unicam.cs.mpgc.rpg125668.gui.controller;

import it.unicam.cs.mpgc.rpg125668.gui.SceneManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SetupController {

    @FXML private Button newGameBtn;
    @FXML private Button loadGameBtn;
    @FXML private Button exitBtn;

    @FXML
    public void initialize() {
        newGameBtn.setOnAction(event -> SceneManager.switchScene("newGame.fxml"));
        loadGameBtn.setOnAction(event -> SceneManager.switchScene("loadGame.fxml"));
        exitBtn.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });
    }
}