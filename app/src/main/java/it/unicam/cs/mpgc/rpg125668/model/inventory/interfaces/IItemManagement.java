package it.unicam.cs.mpgc.rpg125668.model.inventory.interfaces;

import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;

/**
 * Interface for an inventory that contains items and can manage them
 */
public interface IItemManagement {
    /**
     * Add an item to the inventory
     * @param item IItem item to add
     */
    void addItem(IItem item);

    /**
     * Remove an item from the inventory
     * @param item IItem item to remove
     */
    void removeItem(IItem item);

    /**
     * Check if the inventory has an item
     * @param item IItem item to check
     * @return boolean true if the inventory has the item, false otherwise
     */
    boolean hasItem(IItem item);

}
