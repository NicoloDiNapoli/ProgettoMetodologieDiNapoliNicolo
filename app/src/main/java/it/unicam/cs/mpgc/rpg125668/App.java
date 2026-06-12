package it.unicam.cs.mpgc.rpg125668;

import it.unicam.cs.mpgc.rpg125668.logic.data.LoadedData;
import it.unicam.cs.mpgc.rpg125668.logic.dto.GameSaveData;
import it.unicam.cs.mpgc.rpg125668.logic.enumeration.CombatResult;
import it.unicam.cs.mpgc.rpg125668.logic.manager.GameManager;
import it.unicam.cs.mpgc.rpg125668.logic.manager.GameSaveManager;
import it.unicam.cs.mpgc.rpg125668.logic.start.LoadGame;
import it.unicam.cs.mpgc.rpg125668.logic.start.NewGame;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IPurchasable;
import it.unicam.cs.mpgc.rpg125668.model.inventory.Inventory;
import it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces.IRoom;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class App {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== BENVENUTO NEL GIOCO ===");
        System.out.println("  [1] Nuova partita");
        System.out.println("  [2] Carica partita");
        System.out.print("Scelta: ");
        String menuChoice = scanner.nextLine().trim();

        GameManager gm;

        if (menuChoice.equals("2")) {
            gm = handleLoadGame();
            if (gm == null) {
                System.out.println("Nessun salvataggio trovato. Avvio nuova partita...");
                gm = handleNewGame();
            }
        } else {
            gm = handleNewGame();
        }

        // --- GAME LOOP ---
        boolean running = true;
        while (running) {
            printStatus(gm);

            System.out.println("Azioni:");
            System.out.println("  [m] Muoviti");
            if (gm.canAttack()) System.out.println("  [a] Attacca boss");
            if (gm.canShop())   System.out.println("  [s] Negozio");
            if (gm.canLoot())   System.out.println("  [l] Raccogli loot");
            System.out.println("  [i] Inventario");
            System.out.println("  [q] Salva ed esci");

            System.out.print("\nScelta: ");
            String choice = scanner.nextLine().trim().toLowerCase();

            switch (choice) {
                case "m" -> handleMove(gm);
                case "a" -> handleCombat(gm);
                case "s" -> handleShop(gm);
                case "l" -> handleLoot(gm);
                case "i" -> handleInventory(gm);
                case "q" -> {
                    System.out.print("Nome salvataggio: ");
                    String saveName = scanner.nextLine().trim();
                    new GameSaveManager().saveGame(gm.getPlayer(), gm.getGameMap(), saveName);
                    System.out.println("Partita salvata!");
                    running = false;
                }
                default -> System.out.println("Scelta non valida.");
            }
        }

        System.out.println("\nGrazie per aver giocato!");
        scanner.close();
    }


    private static GameManager handleNewGame() {
        System.out.print("Inserisci il tuo nome: ");
        String name = scanner.nextLine();

        LoadedData loadedData = new LoadedData();
        System.out.println("\nSkill disponibili:");
        loadedData.getStudentSkills().forEach(s ->
                System.out.println("  - " + s.getName() + " [" + s.getRarity() + "] danno: " + s.getDamage())
        );
        System.out.print("Scegli una skill: ");
        String skillName = scanner.nextLine();

        NewGame newGame = new NewGame(name, skillName, new Inventory(100, new HashMap<>()), 100);
        System.out.println("\nPartita iniziata! Sei nell'Atrio.\n");
        return newGame.getGameManager();
    }

    private static GameManager handleLoadGame() {
        LoadedData loadedData = new LoadedData();
        List<GameSaveData> saves = loadedData.getSaves();

        if (saves.isEmpty()) return null;

        System.out.println("\nSalvataggi disponibili:");
        saves.forEach(s ->
                System.out.println("  - " + s.getSaveName() + " | " + s.getSaveTime())
        );
        System.out.print("Nome salvataggio: ");
        String saveName = scanner.nextLine().trim();

        boolean exists = saves.stream().anyMatch(s -> s.getSaveName().equalsIgnoreCase(saveName));
        if (!exists) {
            System.out.println("Salvataggio non trovato.");
            return null;
        }

        System.out.println("\nPartita caricata! Sei in " + saves.stream()
                .filter(s -> s.getSaveName().equalsIgnoreCase(saveName))
                .findFirst().get().getCurrentRoom().getName() + "\n");
        return new LoadGame(saveName).getGameManager();
    }

    private static void printStatus(GameManager gm) {
        System.out.println("\n--- " + gm.getCurrentRoom().getName() + " ---");
        System.out.println("Vita: " + gm.getPlayerLife() + "/" + gm.getPlayerMaxLife() +
                " | Livello: " + gm.getPlayerLevel() +
                " | Concentrazione: " + gm.getPlayerConcentration() + "/" + gm.getPlayerMaxConcentration() +
                " | Preparazione: " + gm.getPlayerPreparation() + "/" + gm.getPlayerMaxPreparation());
        System.out.println("Uscite:");
        gm.getAvailableExits().forEach(r -> System.out.println("  - " + r.getName()));
    }

    private static void handleMove(GameManager gm) {
        System.out.print("Nome stanza: ");
        String roomName = scanner.nextLine();
        IRoom target = gm.getAvailableExits().stream()
                .filter(r -> r.getName().equalsIgnoreCase(roomName))
                .findFirst().orElse(null);
        if (target == null) System.out.println("Stanza non trovata.");
        else gm.move(target);
    }

    private static void handleCombat(GameManager gm) {
        if (!gm.canAttack()) { System.out.println("Non puoi attaccare qui."); return; }

        while (gm.canAttack()) {
            System.out.println("\n⚔ COMBATTIMENTO ⚔");
            System.out.println("Boss: " + gm.getCurrentEnemy().getName() +
                    " | Vita: " + gm.getCurrentEnemy().getLife() +
                    " | Difficoltà: " + gm.getCurrentEnemy().getDifficult());
            System.out.println("Player → Vita: " + gm.getPlayerLife() +
                    " | Concentrazione: " + gm.getPlayerConcentration() +
                    " | Preparazione: " + gm.getPlayerPreparation());

            System.out.println("Le tue skill:");
            gm.getPlayerSkills().forEach(s ->
                    System.out.println("  - " + s.getName() +
                            " | danno: " + s.getDamage() +
                            " | preparazione: " + s.getPreparationRequired())
            );
            System.out.println("  [usa] Usa un item dall'inventario");
            System.out.print("Scegli skill (o 'usa'): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("usa")) { handleInventoryUse(gm); continue; }

            IStudentSkill skill = gm.getPlayerSkills().stream()
                    .filter(s -> s.getName().equalsIgnoreCase(input))
                    .findFirst().orElse(null);
            if (skill == null) { System.out.println("Skill non trovata."); continue; }

            CombatResult result = gm.attack(skill);
            switch (result) {
                case FIGHT -> System.out.println("Turno completato! Vita player: " + gm.getPlayerLife() +
                        " | Vita boss: " + gm.getCurrentEnemy().getLife());
                case WIN -> {
                    System.out.println("Hai sconfitto il boss! Le stanze vengono ripopolate.");
                    System.out.println("Hai sconfitto il boss! Livello attuale: " + gm.getPlayerLevel());
                    gm.restockAll();
                    return;
                }
                case LOSE -> {
                    System.out.println("Sei stato sconfitto! Rinasci nell'Atrio.");
                    gm.respawnPlayer();
                    return;
                }
            }
        }
    }

    private static void handleShop(GameManager gm) {
        if (!gm.canShop()) { System.out.println("Non c'è un negozio qui."); return; }
        System.out.println("Monete disponibili: " + gm.getPlayerCoins());
        System.out.println("Articoli disponibili:");
        gm.getDispenser().getItems().forEach((item, qty) ->
                System.out.println("  - " + item.getName() + " x" + qty +
                        " | prezzo: " + ((IPurchasable) item).getPrice())
        );
        System.out.print("Acquista (nome item o invio per saltare): ");
        String itemName = scanner.nextLine();
        if (!itemName.isBlank()) {
            gm.getDispenser().getItems().keySet().stream()
                    .filter(i -> i.getName().equalsIgnoreCase(itemName))
                    .findFirst()
                    .ifPresentOrElse(
                            i -> { gm.buy((IPurchasable) i); System.out.println("Acquistato!"); },
                            () -> System.out.println("Item non trovato.")
                    );
        }
    }

    private static void handleLoot(GameManager gm) {
        if (!gm.canLoot()) { System.out.println("Niente da raccogliere."); return; }
        gm.loot();
        System.out.println("Loot raccolto!");
    }

    private static void handleInventory(GameManager gm) {
        System.out.println("\n--- INVENTARIO ---");
        System.out.println("Monete: " + gm.getPlayerCoins());
        if (gm.getPlayerItems().isEmpty()) {
            System.out.println("Nessun item.");
        } else {
            System.out.println("Items:");
            gm.getPlayerItems().forEach((item, qty) ->
                    System.out.println("  - " + item.getName() + " x" + qty)
            );
            System.out.print("Usa un item (nome o invio per saltare): ");
            String itemName = scanner.nextLine();
            if (!itemName.isBlank()) useItem(gm, itemName);
        }
        System.out.println("Skill:");
        gm.getPlayerSkills().forEach(s -> System.out.println("  - " + s.getName()));
    }

    private static void handleInventoryUse(GameManager gm) {
        if (gm.getPlayerItems().isEmpty()) { System.out.println("Nessun item nell'inventario."); return; }
        System.out.println("Items:");
        gm.getPlayerItems().forEach((item, qty) ->
                System.out.println("  - " + item.getName() + " x" + qty)
        );
        System.out.print("Usa item (nome): ");
        String itemName = scanner.nextLine();
        useItem(gm, itemName);
    }

    private static void useItem(GameManager gm, String itemName) {
        IItem item = gm.getPlayerItems().keySet().stream()
                .filter(i -> i.getName().equalsIgnoreCase(itemName))
                .findFirst().orElse(null);
        if (item == null) { System.out.println("Item non trovato."); return; }
        gm.useItem(item);
        System.out.println("Item usato!");
    }
}