package it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces;

import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;
import it.unicam.cs.mpgc.rpg125668.model.inventory.interfaces.IInventory;

import java.util.List;

/**
 * Interface for a lootable room that can generate loot in the room
 */
public interface IRoomLootable extends IRoom{
    /**
     * Return the inventory of a lootable Object
     * @return IInventory inventory of the lootable Object
     */
    IInventory loot();

    /**
     * Reset the inventory of a lootable Object
     * @return IInventory inventory of the lootable Object
     */
    IInventory reset();

    /**
     * Generate loot in the room
     * @param items List<IItem> items to add
     * @param coins int coins to add
     */
    void generateLoot(List<IItem> items, int coins);
}
