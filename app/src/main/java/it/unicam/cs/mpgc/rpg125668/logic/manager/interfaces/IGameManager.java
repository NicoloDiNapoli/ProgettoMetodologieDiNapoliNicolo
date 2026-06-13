package it.unicam.cs.mpgc.rpg125668.logic.manager.interfaces;

import it.unicam.cs.mpgc.rpg125668.logic.manager.GameActionResult;
import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IEnemy;
import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IStudent;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IPurchasable;
import it.unicam.cs.mpgc.rpg125668.model.dispenser.interfaces.IShop;
import it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces.IGameMap;
import it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces.IRoom;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IBossSkill;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;

import java.util.List;
import java.util.Map;

public interface IGameManager {

    /**
     * Moves the player to the newRoom if it is an available exit.
     * @return true if the move succeeded, false if the room is not reachable
     */
    boolean move(IRoom newRoom);

    /**
     * execute the player attack action.
     * @param skill IStudentSkill skill to use
     * @return GameActionResult with the result of the attack action.
     */
    GameActionResult executeAttack(IStudentSkill skill);

    /**
     * If there is something to loot in the current room, it is added to the player's inventory.
     * @return GameActionResult with the result of the loot action and the loot details.
     */
    GameActionResult executeLoot();

    /**
     * Buys an item from the shop.
     * @param item IPurchasable extends IItem item to buy
     * @return GameActionResult with the result of the buy action.
     */
    GameActionResult executeBuy(IPurchasable item);

    /**
     * Uses an item. If in combat, the enemy immediately takes its turn after.
     * @return GameActionResult with the result of the use item action.
     */
    GameActionResult executeUseItem(IItem item);

    boolean canAttack();

    boolean canShop();

    boolean canLoot();


    //Getters
    IRoom getCurrentRoom();
    List<IRoom> getAvailableExits();
    IShop getDispenser();
    int getPlayerLife();
    int getPlayerMaxLife();
    int getPlayerConcentration();
    int getPlayerMaxConcentration();
    int getPlayerPreparation();
    int getPlayerMaxPreparation();
    int getPlayerCoins();
    int getPlayerLevel();
    List<IStudentSkill> getPlayerSkills();
    Map<IItem, Integer> getPlayerItems();
    IEnemy<IBossSkill> getCurrentEnemy();
    IStudent<IStudentSkill> getPlayer();
    IGameMap getGameMap();
}
