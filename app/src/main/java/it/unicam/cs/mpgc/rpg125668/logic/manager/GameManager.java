package it.unicam.cs.mpgc.rpg125668.logic.manager;

import it.unicam.cs.mpgc.rpg125668.logic.enumeration.CombatResult;
import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IEnemy;
import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IStudent;
import it.unicam.cs.mpgc.rpg125668.model.consumable.Book;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IPurchasable;
import it.unicam.cs.mpgc.rpg125668.model.dispenser.interfaces.IShop;
import it.unicam.cs.mpgc.rpg125668.model.inventory.interfaces.IInventory;
import it.unicam.cs.mpgc.rpg125668.model.rooms.LootableRoom;
import it.unicam.cs.mpgc.rpg125668.model.rooms.ShopRoom;
import it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces.IGameMap;
import it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces.IRoom;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IBossSkill;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;
import it.unicam.cs.mpgc.rpg125668.utils.LootGenerator;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class GameManager {
    private static final String RESPAWN_ROOM = "Atrio";
    private static final String FIGHTING_ROOM = "Sala Professori";

    private final IStudent<IStudentSkill> player;
    private final List<IEnemy<IBossSkill>> enemies;
    private final IGameMap gameMap;
    private final CombatManager combatManager;
    private final List<IStudentSkill> studentSkillsToUnlock;

    public GameManager(IStudent<IStudentSkill> player, List<IEnemy<IBossSkill>> enemies,
                       IGameMap gameMap, List<IStudentSkill> studentSkillsToUnlock) {
        if (player == null || enemies == null)
            throw new IllegalArgumentException("Illegal arguments: player or enemies is null");
        this.player = player;
        this.enemies = enemies;
        this.gameMap = gameMap;
        this.studentSkillsToUnlock = studentSkillsToUnlock;
        this.combatManager = new CombatManager(player, enemies);
    }


    public void respawnPlayer() {
        player.heal(100);
        player.setConcentration(0);
        player.setPreparation(0);
        gameMap.setCurrentRoom(RESPAWN_ROOM);
    }

    public void restockAll() {
        LootGenerator.generateLoot(this.gameMap);
        LootGenerator.restockDispensers(this.gameMap);
    }

    /**
     * Moves the player to newRoom if it is an available exit.
     * @return true if the move succeeded, false if the room is not reachable
     */
    public boolean move(IRoom newRoom) {
        if (newRoom == null) throw new IllegalArgumentException("Illegal arguments: room is null");
        boolean reachable = this.gameMap.getAvailableExits().stream()
                .anyMatch(room -> room.getName().equals(newRoom.getName()));
        if (reachable) {
            this.gameMap.setCurrentRoom(newRoom.getName());
            return true;
        }
        return false;
    }

    //return true if the player is in a fighting room and there are still enemies alive
    public boolean canAttack() {
        return this.gameMap.getCurrentRoom().getName().equals(FIGHTING_ROOM)
                && !this.combatManager.allEnemiesDefeated();
    }

    //return true if the player is in a shop room
    public boolean canShop(){
        return this.gameMap.getCurrentRoom() instanceof ShopRoom;
    }

    //return true if the player is in a lootable room and there is something to loot
    public boolean canLoot(){
        if (!(this.gameMap.getCurrentRoom() instanceof LootableRoom lootableRoom)) return false;
        return lootableRoom.getLootableCoins() > 0 || !lootableRoom.loot().getItems().isEmpty();
    }

    public IShop getDispenser() {
        return ((ShopRoom) this.gameMap.getCurrentRoom()).getDispenser();
    }

    /**
     * execute the player attack action.
     * @param skill IStudentSkill skill to use
     * @return GameActionResult with the result of the attack action.
     */
    public GameActionResult executeAttack(IStudentSkill skill) {
        if (player.getPreparation() < skill.getPreparationRequired()) {
            return GameActionResult.failure(
                    "[COMBAT] Preparazione insufficiente per lanciare " + skill.getName() + "!");
        }

        IEnemy<IBossSkill> enemy = combatManager.getCurrentEnemy();
        if (enemy == null) {
            return GameActionResult.failure("[COMBAT] Nessun nemico da attaccare.");
        }

        CombatResult result = this.combatManager.fight(skill);

        return switch (result) {
            case FIGHT -> new GameActionResult(CombatResult.FIGHT,
                    "[COMBAT] Usato " + skill.getName() + "! Vita " + enemy.getName() + ": " + enemy.getLife(),
                    true);
            case WIN -> {
                this.restockAll();
                yield new GameActionResult(CombatResult.WIN,
                        "🎉 [VITTORIA] Sconfitto " + enemy.getName() + "! Livello attuale: " + this.getPlayerLevel()
                                + "\n[INFO] Le stanze sono state ripopolate.",
                        true);
            }
            case LOSE -> {
                this.respawnPlayer();
                yield new GameActionResult(CombatResult.LOSE,
                        "💀 [SCONFITTA] Sei andato KO sotto i colpi di " + enemy.getName()
                                + "! Rinasci all'Atrio.",
                        true);
            }
        };
    }

    /**
     * If there is something to loot in the current room, it is added to the player's inventory.
     * @return GameActionResult with the result of the loot action and the loot details.
     */
    public GameActionResult executeLoot() {
        if (!canLoot())
            return GameActionResult.failure("[LOOT] Non c'è nulla da raccogliere in questa stanza.");

        IInventory loot = ((LootableRoom) this.gameMap.getCurrentRoom()).loot();
        int coins = loot.seeCoins();
        int itemsCount = loot.getItems().size();

        this.player.getInventory().addCoins(coins);
        loot.getItems().keySet().forEach(item -> this.player.getInventory().addItem(item));

        return GameActionResult.info(
                "[LOOT] Bottino raccolto: " + coins + " monete e " + itemsCount + " oggetti aggiunti allo zaino.");
    }

    /**
     * Buys an item from the shop.
     * @param item IPurchasable extends IItem item to buy
     * @return GameActionResult with the result of the buy action.
     */
    public GameActionResult executeBuy(IPurchasable item) {
        if (!canShop())
            return GameActionResult.failure("[NEGOZIO] Non c'è nessun distributore qui.");
        if (player.getInventory().seeCoins() < item.getPrice())
            return GameActionResult.failure(
                    "[NEGOZIO] Monete insufficienti per acquistare " + item.getName());

        ((ShopRoom) this.gameMap.getCurrentRoom()).getDispenser().buyItem(this.player, item);
        return GameActionResult.info("[NEGOZIO] Acquistato con successo: " + item.getName());
    }

    /**
     * Uses an item. If in combat, the enemy immediately takes its turn after.
     * @return GameActionResult with the result of the use item action.
     */
    public GameActionResult executeUseItem(IItem item) {
        if (!player.getInventory().getItems().containsKey(item))
            return GameActionResult.failure("[ZAINO] Errore: l'oggetto non è presente nell'inventario.");

        if( item instanceof Book book){
            if (this.player.getConcentration() < book.getRarity().getConcentrationRequired()) {
                return GameActionResult.failure("[ZAINO] Non hai abbastanza concentrazione per usare: " + item.getName() +
                        " (Richiesta: " + book.getRarity().getConcentrationRequired() + ", Attuale: " + this.player.getConcentration() + ")");
            }
        }

        if (this.canAttack()) {
            applyItem(item);
            CombatResult result = this.combatManager.enemyTurn();
            if (result == CombatResult.LOSE) {
                this.respawnPlayer();
                return new GameActionResult(CombatResult.LOSE,
                        "[ZAINO] Hai usato: " + item.getName()
                                + "\n💀 [SCONFITTA] Il contrattacco del Boss ti ha mandato KO! Rinasci all'Atrio.",
                        true);
            }
            return new GameActionResult(CombatResult.FIGHT,
                    "[ZAINO] Hai usato " + item.getName() + " durante il combattimento. Il Boss risponde al turno!",
                    true);
        } else {
            applyItem(item);
            return GameActionResult.info("[ZAINO] Hai usato: " + item.getName());
        }
    }


    private void applyItem(IItem item) {
        if (item instanceof Book book) {
            this.player.useItem(item);
            IStudentSkill newSkill = this.generateSkill(book, this.studentSkillsToUnlock);
            if (newSkill != null) this.player.addSkill(newSkill);
        } else {
            this.player.useItem(item);
        }
    }

    // Generate a skill based on the book's rarity and the available skills.'
    private IStudentSkill generateSkill(Book book, List<IStudentSkill> skills) {
        if (new Random().nextDouble() < book.getRarity().getProbabilitySkill()) return null;

        List<IStudentSkill> available = skills.stream()
                .filter(s -> !this.player.getSkills().contains(s))
                .filter(s -> s.getRarity().ordinal() <= book.getRarity().ordinal())
                .toList();

        if (available.isEmpty()) return null;
        return available.get(new Random().nextInt(available.size()));
    }

    //Getters
    public IStudent<IStudentSkill> getPlayer(){return this.player;}
    public IGameMap getGameMap(){return this.gameMap;}
    public int getPlayerMaxLife(){ return this.player.getMaxLife();}
    public int getPlayerConcentration(){ return this.player.getConcentration();}
    public int getPlayerMaxConcentration(){ return this.player.getMaxConcentration();}
    public int getPlayerPreparation(){ return this.player.getPreparation();}
    public int getPlayerMaxPreparation(){ return this.player.getMaxPreparation();}
    public IEnemy<IBossSkill> getCurrentEnemy(){ return this.combatManager.getCurrentEnemy();}
    public List<IStudentSkill> getPlayerSkills(){ return this.player.getSkills();}
    public int getPlayerLife(){ return this.player.getLife();}
    public int getPlayerCoins(){ return this.player.getInventory().seeCoins();}
    public Map<IItem, Integer> getPlayerItems(){ return this.player.getInventory().getItems();}
    public IRoom getCurrentRoom(){ return this.gameMap.getCurrentRoom();}
    public List<IRoom> getAvailableExits(){ return this.gameMap.getAvailableExits();}
    public int getPlayerLevel(){ return this.player.getLevel().getLevel();}
}