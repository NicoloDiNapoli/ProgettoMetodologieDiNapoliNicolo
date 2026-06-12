package it.unicam.cs.mpgc.rpg125668;


import it.unicam.cs.mpgc.rpg125668.gui.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        SceneManager.setRootStage(primaryStage);
        primaryStage.setTitle("HerosOfUniversity");
        SceneManager.switchScene("setup.fxml");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}