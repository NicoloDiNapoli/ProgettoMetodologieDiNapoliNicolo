package it.unicam.cs.mpgc.rpg125668.gui.controller;

import it.unicam.cs.mpgc.rpg125668.gui.SceneManager;
import it.unicam.cs.mpgc.rpg125668.logic.manager.GameActionResult;
import it.unicam.cs.mpgc.rpg125668.logic.manager.GameManager;
import it.unicam.cs.mpgc.rpg125668.logic.manager.GameSaveManager;
import it.unicam.cs.mpgc.rpg125668.logic.start.Game;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IPurchasable;
import it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces.IRoom;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.Optional;

public class GamePlayController {

    // Stats player
    @FXML private Label roomLabel;
    @FXML private ProgressBar lifeBar;
    @FXML private Label lifeText;
    @FXML private ProgressBar concentrationBar;
    @FXML private Label concentrationText;
    @FXML private ProgressBar prepBar;
    @FXML private Label prepText;
    @FXML private Label coinsLabel;

    // Rooms panel
    @FXML private ListView<String> exitsList;
    @FXML private Button moveBtn;
    @FXML private Button lootBtn;
    @FXML private Button shopBtn;

    // Log panel
    @FXML private TextArea gameLog;

    // Combat / skills / inventory panel
    @FXML private VBox combatBox;
    @FXML private Label enemyName;
    @FXML private Label enemyLife;
    @FXML private ListView<String> skillList;
    @FXML private Button attackBtn;
    @FXML private ListView<String> inventoryList;
    @FXML private Button useItemBtn;

    // Quit button
    @FXML private Button quitBtn;

    private GameManager gm;

    @FXML
    public void initialize() {
        moveBtn.setOnAction(event -> handleMove());
        lootBtn.setOnAction(event -> handleLoot());
        shopBtn.setOnAction(event -> handleShop());
        attackBtn.setOnAction(event -> handleAttack());
        useItemBtn.setOnAction(event -> handleUseItem());
        quitBtn.setOnAction(event -> handleSaveAndQuit());
    }

    public void initData(Game game) {
        this.gm = game.getGameManager();
        logMessage("=== PARTITA INIZIATA ===");
        logMessage("Sei in: " + gm.getCurrentRoom().getName());
        updateUI();
    }

    /**
     * Updates the UI with the current game state.
     */
    private void updateUI() {
        roomLabel.setText(gm.getCurrentRoom().getName().toUpperCase());

        double lifeProgress = (double) gm.getPlayerLife() / gm.getPlayerMaxLife();
        lifeBar.setProgress(lifeProgress);
        lifeText.setText(gm.getPlayerLife() + "/" + gm.getPlayerMaxLife());

        double concProgress = (double) gm.getPlayerConcentration() / gm.getPlayerMaxConcentration();
        concentrationBar.setProgress(concProgress);
        concentrationText.setText(gm.getPlayerConcentration() + "/" + gm.getPlayerMaxConcentration());

        double prepProgress = (double) gm.getPlayerPreparation() / gm.getPlayerMaxPreparation();
        prepBar.setProgress(prepProgress);
        prepText.setText(gm.getPlayerPreparation() + "/" + gm.getPlayerMaxPreparation());

        coinsLabel.setText("MONETE: " + gm.getPlayerCoins());

        exitsList.getItems().clear();
        gm.getAvailableExits().forEach(room -> exitsList.getItems().add(room.getName()));

        lootBtn.setDisable(!gm.canLoot());
        shopBtn.setDisable(!gm.canShop());
        attackBtn.setDisable(!gm.canAttack());

        if (gm.canAttack() && gm.getCurrentEnemy() != null) {
            combatBox.setStyle("-fx-background-color: #e74c3c; -fx-padding: 8; -fx-background-radius: 5;");
            enemyName.setText(gm.getCurrentEnemy().getName());
            enemyLife.setText("Vita: " + gm.getCurrentEnemy().getLife()
                    + " [Difficoltà: " + gm.getCurrentEnemy().getDifficult() + "]");
        } else {
            combatBox.setStyle("-fx-background-color: #7f8c8d; -fx-padding: 8; -fx-background-radius: 5;");
            enemyName.setText("Nessun Nemico Presente");
            enemyLife.setText("Vita: --");
        }

        skillList.getItems().clear();
        gm.getPlayerSkills().forEach(s ->
                skillList.getItems().add(s.getName() + " (Danno: " + s.getDamage() + " | Prep: " + s.getPreparationRequired() + ")")
        );

        inventoryList.getItems().clear();
        gm.getPlayerItems().forEach((item, qty) ->
                inventoryList.getItems().add(item.getName() + " x" + qty)
        );
    }


    /**
     * Handles the move action by selecting a room from the list and moving to it.
     */
    private void handleMove() {
        String selectedRoomName = exitsList.getSelectionModel().getSelectedItem();
        if (selectedRoomName == null) {
            logMessage("[INFO] Seleziona una stanza dalla lista per muoverti.");
            return;
        }

        IRoom target = gm.getAvailableExits().stream()
                .filter(r -> r.getName().equalsIgnoreCase(selectedRoomName))
                .findFirst().orElse(null);

        if (target != null && gm.move(target)) {
            logMessage("Ti sei spostato in: " + target.getName());
            updateUI();
        }
    }

    /**
     * Handles the loot action by executing the loot action and updating the UI.
     */
    private void handleLoot() {
        GameActionResult result = gm.executeLoot();
        logMessage(result.getMessage());
        if (result.isSuccess()) updateUI();
    }

    /**
     * Handles the shop action by displaying a dialog to select an item and buying it.
     */
    private void handleShop() {
        if (!gm.canShop()) return;

        ChoiceDialog<String> dialog = new ChoiceDialog<>();
        dialog.setTitle("Negozio Universitario");
        dialog.setHeaderText("Monete disponibili: " + gm.getPlayerCoins() + "\nScegli un articolo da acquistare:");

        gm.getDispenser().getItems().forEach((item, qty) -> {
            if (qty > 0)
                dialog.getItems().add(item.getName() + " (Prezzo: " + ((IPurchasable) item).getPrice() + ")");
        });

        if (dialog.getItems().isEmpty()) {
            logMessage("[NEGOZIO] Esaurito! Non ci sono articoli disponibili.");
            return;
        }

        Optional<String> selection = dialog.showAndWait();
        selection.ifPresent(choice -> {
            String pureItemName = choice.split(" \\(")[0];

            IPurchasable selectedItem = (IPurchasable) gm.getDispenser().getItems().keySet().stream()
                    .filter(i -> i.getName().equalsIgnoreCase(pureItemName))
                    .findFirst().orElse(null);

            if (selectedItem != null) {
                GameActionResult result = gm.executeBuy(selectedItem);
                logMessage(result.getMessage());
                if (result.isSuccess()) updateUI();
            }
        });
    }

    /**
     * Handle the attack action by selecting a skill from the list and executing it.
     */
    private void handleAttack() {
        int selectedIndex = skillList.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            logMessage("[ATTENZIONE] Seleziona una Skill dalla lista prima di attaccare!");
            return;
        }
        //check if the selected index is valid to avoid array out of bounds exception
        if (selectedIndex >= gm.getPlayerSkills().size()) {
            logMessage("[ERRORE] Selezione non valida. Riprova.");
            return;
        }

        IStudentSkill skill = gm.getPlayerSkills().get(selectedIndex);
        GameActionResult result = gm.executeAttack(skill);
        logMessage(result.getMessage());
        updateUI();
    }

    /**
     * Handles the use item action by selecting an item from the inventory and using it.
     */
    private void handleUseItem() {
        String selectedItemRow = inventoryList.getSelectionModel().getSelectedItem();
        if (selectedItemRow == null) {
            logMessage("[INFO] Seleziona un oggetto dallo zaino per usarlo.");
            return;
        }

        String pureItemName = selectedItemRow.split(" x")[0];

        IItem item = gm.getPlayerItems().keySet().stream()
                .filter(i -> i.getName().equalsIgnoreCase(pureItemName))
                .findFirst().orElse(null);

        if (item != null) {
            GameActionResult result = gm.executeUseItem(item);
            logMessage(result.getMessage());
            updateUI();
        }
    }

    /**
     * Handles the save and quit action by asking the player if they want to save their game
     * and then switching to the setup scene.
     * There are 3 options: (Save and Quit, Exit and don't save, Go back to game)
     */
    private void handleSaveAndQuit() {
        // Ask the player if they want to save before quitting
        ButtonType btnSave    = new ButtonType("Salva ed Esci");
        ButtonType btnNoSave  = new ButtonType("Esci senza salvare");
        ButtonType btnCancel  = new ButtonType("Annulla", ButtonBar.ButtonData.CANCEL_CLOSE);

        Alert choiceAlert = new Alert(Alert.AlertType.CONFIRMATION);
        choiceAlert.setTitle("Uscita");
        choiceAlert.setHeaderText("Vuoi salvare prima di uscire?");
        choiceAlert.getButtonTypes().setAll(btnSave, btnNoSave, btnCancel);

        Optional<ButtonType> choice = choiceAlert.showAndWait();
        if (choice.isEmpty() || choice.get() == btnCancel) return;

        if (choice.get() == btnNoSave) {
            SceneManager.switchScene("setup.fxml");
            return;
        }

        // If player chooses to save, ask for a save name
        TextInputDialog nameDialog = new TextInputDialog("Salvataggio_1");
        nameDialog.setTitle("Salva ed Esci");
        nameDialog.setHeaderText("Inserisci il nome del salvataggio:");
        nameDialog.setContentText("Nome:");

        Optional<String> saveName = nameDialog.showAndWait();
        saveName.ifPresent(name -> {
            if (!name.trim().isEmpty()) {
                new GameSaveManager().saveGame(gm.getPlayer(), gm.getGameMap(), name.trim());
                logMessage("Gioco salvato come: " + name.trim());
                SceneManager.switchScene("setup.fxml");
            }
        });
    }

    private void logMessage(String message) {
        gameLog.appendText("\n" + message);
    }
}