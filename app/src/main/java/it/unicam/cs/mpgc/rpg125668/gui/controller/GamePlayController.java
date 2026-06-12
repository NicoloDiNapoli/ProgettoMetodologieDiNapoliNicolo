package it.unicam.cs.mpgc.rpg125668.gui.controller;

import it.unicam.cs.mpgc.rpg125668.gui.SceneManager;
import it.unicam.cs.mpgc.rpg125668.logic.enumeration.CombatResult;
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

    //Stats player
    @FXML private Label roomLabel;
    @FXML private ProgressBar lifeBar;
    @FXML private Label lifeText;
    @FXML private ProgressBar concentrationBar;
    @FXML private Label concentrationText;
    @FXML private ProgressBar prepBar;
    @FXML private Label prepText;
    @FXML private Label coinsLabel;

    //rooms panel
    @FXML private ListView<String> exitsList;
    @FXML private Button moveBtn;
    @FXML private Button lootBtn;
    @FXML private Button shopBtn;

    //log panel
    @FXML private TextArea gameLog;

    //skills panel, inventory panel, combat panel
    @FXML private VBox combatBox;
    @FXML private Label enemyName;
    @FXML private Label enemyLife;
    @FXML private ListView<String> skillList;
    @FXML private Button attackBtn;
    @FXML private ListView<String> inventoryList;
    @FXML private Button useItemBtn;

    //quit button
    @FXML private Button quitBtn;

    //game manager
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

    private void updateUI() {
        // Current Room
        roomLabel.setText(gm.getCurrentRoom().getName().toUpperCase());

        // Life
        double lifeProgress = (double) gm.getPlayerLife() / gm.getPlayerMaxLife();
        lifeBar.setProgress(lifeProgress);
        lifeText.setText(gm.getPlayerLife() + "/" + gm.getPlayerMaxLife());

        // Concentration
        double concProgress = (double) gm.getPlayerConcentration() / gm.getPlayerMaxConcentration();
        concentrationBar.setProgress(concProgress);
        concentrationText.setText(gm.getPlayerConcentration() + "/" + gm.getPlayerMaxConcentration());

        // Preparation
        double prepProgress = (double) gm.getPlayerPreparation() / gm.getPlayerMaxPreparation();
        prepBar.setProgress(prepProgress);
        prepText.setText(gm.getPlayerPreparation() + "/" + gm.getPlayerMaxPreparation());

        // Coins
        coinsLabel.setText("MONETE: " + gm.getPlayerCoins());

        // Available exits
        exitsList.getItems().clear();
        gm.getAvailableExits().forEach(room -> exitsList.getItems().add(room.getName()));

        // Button
        lootBtn.setDisable(!gm.canLoot());
        shopBtn.setDisable(!gm.canShop());
        attackBtn.setDisable(!gm.canAttack());

        // Enemy stats
        if (gm.canAttack() && gm.getCurrentEnemy() != null) {
            combatBox.setStyle("-fx-background-color: #e74c3c; -fx-padding: 8; -fx-background-radius: 5;");
            enemyName.setText(gm.getCurrentEnemy().getName());
            enemyLife.setText("Vita: " + gm.getCurrentEnemy().getLife() + " [Difficoltà: " + gm.getCurrentEnemy().getDifficult() + "]");
        } else {
            combatBox.setStyle("-fx-background-color: #7f8c8d; -fx-padding: 8; -fx-background-radius: 5;");
            enemyName.setText("Nessun Nemico Presente");
            enemyLife.setText("Vita: --");
        }

        // Skills player
        skillList.getItems().clear();
        gm.getPlayerSkills().forEach(s ->
                skillList.getItems().add(s.getName() + " (Danno: " + s.getDamage() + " | Prep: " + s.getPreparationRequired() + ")")
        );

        // Inventory player
        inventoryList.getItems().clear();
        gm.getPlayerItems().forEach((item, qty) ->
                inventoryList.getItems().add(item.getName() + " x" + qty)
        );
    }

    /**
     * Handle the move action.
     * If the selected room is valid, the player moves to it.
     * */
    private void handleMove() {
        String selectedRoomName = exitsList.getSelectionModel().getSelectedItem();
        if (selectedRoomName == null) {
            logMessage("[INFO] Seleziona una stanza dalla lista per muoverti.");
            return;
        }

        IRoom target = gm.getAvailableExits().stream()
                .filter(r -> r.getName().equalsIgnoreCase(selectedRoomName))
                .findFirst().orElse(null);

        if (target != null) {
            gm.move(target);
            logMessage("Ti sei spostato in: " + target.getName());
            updateUI();
        }
    }

    /**
     * Handle the loot action.
     * If the player can loot, the player loots.
     */
    private void handleLoot() {
        if (gm.canLoot()) {
            gm.loot();
            logMessage("[LOOT] Bottino raccolto con successo!");
            updateUI();
        }
    }

    /**
     * Handle the shop action.
     * If the player can shop, the player buys an item from the shop.
     */
    private void handleShop() {
        if (!gm.canShop()) return;

        ChoiceDialog<String> dialog = new ChoiceDialog<>();
        dialog.setTitle("Negozio Universitario");
        dialog.setHeaderText("Monete disponibili: " + gm.getPlayerCoins() + "\nScegli un articolo da acquistare:");

        gm.getDispenser().getItems().forEach((item, qty) -> {
            if (qty > 0) {
                dialog.getItems().add(item.getName() + " (Prezzo: " + ((IPurchasable) item).getPrice() + ")");
            }
        });

        if (dialog.getItems().isEmpty()) {
            logMessage("[NEGOZIO] Esaurito! Non ci sono articoli disponibili.");
            return;
        }

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(selection -> {
            String pureItemName = selection.split(" \\(")[0];

            IPurchasable selectedItem = (IPurchasable) gm.getDispenser().getItems().keySet().stream()
                    .filter(i -> i.getName().equalsIgnoreCase(pureItemName))
                    .findFirst().orElse(null);

            if (selectedItem != null) {
                if (gm.getPlayerCoins() >= selectedItem.getPrice()) {
                    gm.buy(selectedItem);
                    logMessage("[NEGOZIO] Acquistato: " + pureItemName);
                    updateUI();
                } else {
                    logMessage("[NEGOZIO] Monete insufficienti per acquistare " + pureItemName);
                }
            }
        });
    }


    private void handleAttack() {
        int selectedIndex = skillList.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            logMessage("[ATTENZIONE] Seleziona una Skill dalla lista prima di attaccare!");
            return;
        }

        IStudentSkill skill = gm.getPlayerSkills().get(selectedIndex);

        if (gm.getPlayerPreparation() < skill.getPreparationRequired()) {
            logMessage("[COMBAT] Preparazione insufficiente per lanciare " + skill.getName() + "!");
            return;
        }

        CombatResult result = gm.attack(skill);
        switch (result) {
            case FIGHT -> {
                logMessage("[COMBAT] Usato " + skill.getName() + "! Vita Boss: " + gm.getCurrentEnemy().getLife());
                updateUI();
            }
            case WIN -> {
                logMessage("🎉 [VITTORIA] Sconfitto il Boss! Livello attuale: " + gm.getPlayerLevel());
                logMessage("[INFO] Le stanze sono state ripopolate di nuovi pericoli.");
                gm.restockAll();
                updateUI();
            }
            case LOSE -> {
                logMessage("💀 [SCONFITTA] Sei andato KO! Rinasci all'Atrio di partenza.");
                gm.respawnPlayer();
                updateUI();
            }
        }
    }

    /**
     * Handle the use item action.
     * If the player can use an item, the player uses it.
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
            if (gm.canAttack()) {
                CombatResult result = gm.useItemInCombat(item);
                logMessage("[ZAINO] Hai usato: " + pureItemName);
                if (result == CombatResult.LOSE) {
                    logMessage("💀 [SCONFITTA] Sei andato KO! Rinasci all'Atrio di partenza.");
                    gm.respawnPlayer();
                }
            } else {
                gm.useItem(item);
                logMessage("[ZAINO] Hai usato: " + pureItemName);
            }
            updateUI();
        }
    }

    /**
     * Handle the save and quit action.
     * If the player wants to save the game, a dialog is shown to enter a name for the save.
     */
    private void handleSaveAndQuit() {
        TextInputDialog dialog = new TextInputDialog("Salvataggio_1");
        dialog.setTitle("Salva ed Esci");
        dialog.setHeaderText("Vuoi salvare lo stato dell'avventura?");
        dialog.setContentText("Inserisci il nome del salvataggio:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(saveName -> {
            if (!saveName.trim().isEmpty()) {
                new GameSaveManager().saveGame(gm.getPlayer(), gm.getGameMap(), saveName.trim());
                logMessage("Gioco salvato come: " + saveName);
                SceneManager.switchScene("setup.fxml");
            }
        });
    }

    private void logMessage(String message) {
        gameLog.appendText("\n" + message);
    }
}