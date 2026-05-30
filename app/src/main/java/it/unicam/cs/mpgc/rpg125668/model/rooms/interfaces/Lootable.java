package it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces;

import it.unicam.cs.mpgc.rpg125668.model.inventory.interfaces.ICurrencyManagement;
import it.unicam.cs.mpgc.rpg125668.model.inventory.interfaces.IInventory;
import it.unicam.cs.mpgc.rpg125668.model.inventory.interfaces.IItemManagement;

public interface Lootable extends ICurrencyManagement, IItemManagement {
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
}
