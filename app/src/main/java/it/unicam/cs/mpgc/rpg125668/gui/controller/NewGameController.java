package it.unicam.cs.mpgc.rpg125668.gui.controller;

import it.unicam.cs.mpgc.rpg125668.gui.SceneManager;
import it.unicam.cs.mpgc.rpg125668.logic.start.NewGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Controller for the new game scene
 */
public class NewGameController {

    @FXML private TextField playerNameField;
    @FXML private Button heroOneBtn;
    @FXML private Button heroTwoBtn;
    @FXML private Button heroThreeBtn;
    @FXML private Button heroFourBtn;
    @FXML private Button backBtn;

    @FXML
    public void initialize() {
        backBtn.setOnAction(event -> SceneManager.switchScene("setup.fxml"));


        heroOneBtn.setUserData(new StarterValues("Appunti Incantati", 100, 100));
        heroTwoBtn.setUserData(new StarterValues("Inchiostro Ustionante",  100, 100));
        heroThreeBtn.setUserData(new StarterValues("Sigillo del Ragionamento", 85, 80));
        heroFourBtn.setUserData(new StarterValues("Dardi Vettoriali", 85, 80));

        updateButtonText(heroOneBtn);
        updateButtonText(heroTwoBtn);
        updateButtonText(heroThreeBtn);
        updateButtonText(heroFourBtn);

        heroOneBtn.setOnAction(this::handleHeroSelection);
        heroTwoBtn.setOnAction(this::handleHeroSelection);
        heroThreeBtn.setOnAction(this::handleHeroSelection);
        heroFourBtn.setOnAction(this::handleHeroSelection);
    }

    /**
     * Method that handles the selection of a hero.
     * @param event the ActionEvent that triggered the method.
     */
    private void handleHeroSelection(ActionEvent event) {
        String playerName = playerNameField.getText().trim();

        if (playerName.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Attenzione");
            alert.setHeaderText("Nome eroe mancante");
            alert.setContentText("Inserisci un nome prima di selezionare la tua skill!");
            alert.showAndWait();
            return;
        }

        Button bottoneCliccato = (Button) event.getSource();
        StarterValues data = (StarterValues) bottoneCliccato.getUserData();


        NewGame newGame = new NewGame(playerName, data.skillName, data.preparation, data.life);
        FXMLLoader loader = SceneManager.switchScene("gamePlay.fxml");
        GamePlayController gameplayController = loader.getController();
        gameplayController.initData(newGame);
    }

    /**
     * Updates the text of a button based on the user data.
     * @param button the button to update.
     */
    private void updateButtonText(Button button) {
        StarterValues data = (StarterValues) button.getUserData();
        if (data != null) {
            button.setText(
                    "Seleziona " + data.skillName + " (Preparazione iniziale: " + data.preparation + "/Vita iniziale: " + data.life + ")"
            );
        }
    }
}