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
    private IStudent<IStudentSkill> player;
    private List<IEnemy<IBossSkill>> enemies;
    private final IGameMap gameMap;
    private final CombatManager combatManager;
    private final List<IStudentSkill> studentSkillsToUnlock;

    public GameManager(IStudent<IStudentSkill> player, List<IEnemy<IBossSkill>> enemies, IGameMap gameMap, List<IStudentSkill> studentSkillsToUnlock) {
        if(player == null || enemies == null) throw new IllegalArgumentException("Illegal arguments: player or enemies is null");
        this.player = player;
        this.enemies = enemies;
        this.gameMap = gameMap;
        this.studentSkillsToUnlock = studentSkillsToUnlock;
        this.combatManager = new CombatManager(player, enemies);
    }

    //if player is dead respawn him in the starting room
    public void respawnPlayer() {
        player.heal(100);
        player.setConcentration(0);
        player.setPreparation(0);
        gameMap.setCurrentRoom(RESPAWN_ROOM);
    }

    //if the player wins the game, generate the loot in the lootable rooms and restock the dispensers
    public void restockAll() {
        LootGenerator.generateLoot(this.gameMap);
        LootGenerator.restockDispensers(this.gameMap);
    }

    //move the player to the new room
    public void move(IRoom newRoom){
        if(newRoom == null) throw new IllegalArgumentException("Illegal arguments: room is null");
        this.gameMap.getAvailableExits().stream()
                .filter(room -> room.getName().equals(newRoom.getName()))
                .findFirst()
                .ifPresent(room -> {
                    this.gameMap.setCurrentRoom(room.getName());
                });
    }

    //return true if player is in Sala Professori and there are still enemies alive
    public boolean canAttack() {
        return this.gameMap.getCurrentRoom().getName().equals(FIGHTING_ROOM)
                && !this.combatManager.allEnemiesDefeated();
    }

    //return true if player is in a shop room
    public boolean canShop() {
        return this.gameMap.getCurrentRoom() instanceof ShopRoom;
    }

    //return true if player is in a lootable room
    public boolean canLoot() {
        if(!(this.gameMap.getCurrentRoom() instanceof LootableRoom lootableRoom)) return false;
        return lootableRoom.getLootableCoins() > 0 || !lootableRoom.loot().getItems().isEmpty();
    }

    public CombatResult attack(IStudentSkill skill) {
        return this.combatManager.fight(skill);
    }

    //return the dispenser of the current room
    public IShop getDispenser() {
        return ((ShopRoom) this.gameMap.getCurrentRoom()).getDispenser();
    }

    //if the player is in a lootable room, loot it and add the coins and items to the player inventory
    public void loot() {
        IInventory loot = ((LootableRoom) this.gameMap.getCurrentRoom()).loot();
        if(loot.seeCoins() == 0 && loot.getItems().isEmpty()) return;
        this.player.getInventory().addCoins(loot.seeCoins());
        loot.getItems().keySet().forEach(item -> this.player.getInventory().addItem(item));
    }

    //buy an item from the shop dispenser
    public void buy(IPurchasable item) {
        ((ShopRoom)this.gameMap.getCurrentRoom())
                .getDispenser()
                .buyItem(this.player, item);
    }

    //use an item
    public void useItem(IItem item) {
        if(item instanceof Book book) {
            this.player.useItem(item);
            IStudentSkill newSkill = this.generateSkill(book, this.studentSkillsToUnlock);
            if (newSkill != null) this.player.addSkill(newSkill);
        }
        else this.player.useItem(item);
    }

    /**
     * Uses an item during combat: applies its effect, then the enemy takes its turn.
     * @param item the item to use
     * @return LOSE if the enemy kills the player after the item use, FIGHT otherwise
     */
    public CombatResult useItemInCombat(IItem item) {
        useItem(item);
        return this.combatManager.enemyTurn();
    }

    /**
     * private method that generates a new skill based on the book and the list of skills of the student.
     * @param book the book to use to generate the new skill
     * @param skills the list of skills that the student can use
     * @return the new skill generated or null if the book doesn't generate a new skill
     */
    private IStudentSkill generateSkill(Book book, List<IStudentSkill> skills) {
        if (new Random().nextDouble() < book.getRarity().getProbabilitySkill()) return null;

        //availableSkills is a list of skills that the player doesn't have yet and
        //that have the same rarity or less than the book's rarity
        List<IStudentSkill> availableSkills = skills.stream()
                .filter(s -> !this.getPlayer().getSkills().contains(s)) // skill che il player non ha già
                .filter(s -> s.getRarity().ordinal() <= book.getRarity().ordinal())
                .toList();

        if (availableSkills.isEmpty()) return null;
        //pick a random skill from the list of available skills
        return availableSkills.get(new Random().nextInt(availableSkills.size()));
    }

    //getters
    public IStudent<IStudentSkill> getPlayer() { return player; }
    public IGameMap getGameMap() { return gameMap; }
    public int getPlayerMaxLife(){ return player.getMaxLife(); }
    public int getPlayerConcentration(){ return player.getConcentration(); }
    public int getPlayerMaxConcentration(){ return player.getMaxConcentration(); }
    public int getPlayerPreparation(){ return player.getPreparation(); }
    public int getPlayerMaxPreparation(){ return player.getMaxPreparation(); }
    public IEnemy<IBossSkill> getCurrentEnemy() { return combatManager.getCurrentEnemy(); }
    public List<IStudentSkill> getPlayerSkills() { return player.getSkills(); }
    public int getPlayerLife() { return player.getLife(); }
    public int getPlayerCoins() { return player.getInventory().seeCoins(); }
    public Map<IItem, Integer> getPlayerItems() { return player.getInventory().getItems(); }
    public IRoom getCurrentRoom() { return gameMap.getCurrentRoom(); }
    public List<IRoom> getAvailableExits() { return gameMap.getAvailableExits(); }
    public int getPlayerLevel() {return this.player.getLevel().getLevel();}
}
