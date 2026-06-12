package it.unicam.cs.mpgc.rpg125668.gui.controller;

import it.unicam.cs.mpgc.rpg125668.gui.SceneManager;
import it.unicam.cs.mpgc.rpg125668.logic.data.LoadedData;
import it.unicam.cs.mpgc.rpg125668.logic.start.LoadGame;
import it.unicam.cs.mpgc.rpg125668.logic.dto.GameSaveData;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

/**
 * Controller for the load game scene
 */
public class LoadGameController {

    @FXML private TableView<GameSaveData> saveTable;
    @FXML private TableColumn<GameSaveData, String> nameCol;
    @FXML private TableColumn<GameSaveData, Integer> levelCol;
    @FXML private TableColumn<GameSaveData, String> roomCol;
    @FXML private TableColumn<GameSaveData, String> dateCol;

    @FXML private Button loadSelectedBtn;
    @FXML private Button backBtn;

    @FXML
    public void initialize() {

        backBtn.setOnAction(event -> SceneManager.switchScene("setup.fxml"));

        setupTableColumns();

        populateSaveTable();

        loadSelectedBtn.setOnAction(event -> handleLoadGame());
    }

    //Shows columns in the table
    private void setupTableColumns() {
        nameCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getSaveName()));

        levelCol.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getPlayer().getLevel().getLevel()).asObject());

        roomCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCurrentRoom().getName()));

        dateCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().toString()));
    }

    //add values taken from db to the table
    private void populateSaveTable() {
        List<GameSaveData> saves = fetchSavesFromDatabase();

        if (saves == null || saves.isEmpty()) {
            saveTable.setPlaceholder(new javafx.scene.control.Label("Nessun salvataggio presente nel Database."));
            loadSelectedBtn.setDisable(true);
            return;
        }

        ObservableList<GameSaveData> observableSaves = FXCollections.observableArrayList(saves);
        saveTable.setItems(observableSaves);
    }

    /**
     * Method that handles the selection of a save from the table and loads the game.
     */
    private void handleLoadGame() {
        GameSaveData selectedSave = saveTable.getSelectionModel().getSelectedItem();

        if (selectedSave == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Attenzione");
            alert.setHeaderText("Nessuna selezione");
            alert.setContentText("Seleziona un salvataggio dalla tabella prima di premere Carica!");
            alert.showAndWait();
            return;
        }

        try {
            LoadGame loadedGame = new LoadGame(selectedSave.getSaveName());

            FXMLLoader loader = SceneManager.switchScene("gamePlay.fxml");
            if (loader != null) {
                GamePlayController gameplayController = loader.getController();
                gameplayController.initData(loadedGame);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Errore di Caricamento");
            alert.setHeaderText("Impossibile caricare il file");
            alert.setContentText("Dettaglio errore: " + e.getMessage());
            alert.showAndWait();
        }
    }

    private List<GameSaveData> fetchSavesFromDatabase() {
        return new LoadedData().getSaves();
    }
}