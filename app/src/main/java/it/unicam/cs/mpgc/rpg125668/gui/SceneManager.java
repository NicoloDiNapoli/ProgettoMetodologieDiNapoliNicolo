package it.unicam.cs.mpgc.rpg125668.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneManager {

    //main Stage
    private static Stage rootStage;

    /**
     * set the root stage of the application with the stage passed as parameter
     * @param stage the stage to set as root
     */
    public static void setRootStage(Stage stage) {
        rootStage = stage;
    }

    /**
     * Switch the scene of the application to the one specified by the fxmlFileName parameter.
     * If the scene does not exist yet, it is created from the FXML file.
     * If the scene already exists, it is simply changed.
     * @param fxmlFileName the name of the FXML file to load
     * @return the FXMLLoader used to load the FXML file
     */
    public static FXMLLoader switchScene(String fxmlFileName) {
        try {

            String resourcePath = "/it/unicam/cs/mpgc/rpg125668/gui/" + fxmlFileName;

            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(resourcePath));
            Parent root = loader.load();

            //if the scene does not exist yet, create it from the FXML file
            if (rootStage.getScene() == null) {
                rootStage.setScene(new Scene(root));
            } else {
                //if the scene already exists, change it
                rootStage.getScene().setRoot(root);
            }

            rootStage.sizeToScene();
            return loader;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}