package it.unicam.cs.mpgc.rpg125668.model.inventory.interfaces;

import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;

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

    /**
     * Get the quantity of an item in the inventory
     * @param item IItem item to check
     * @return int quantity of the item
     */
    int getItemQuantity(IItem item);

    /**
     * Remove an item quantity from the inventory
     * @param item IItem item to remove
     * @param quantity int quantity of the item to remove
     */
    void removeItemQuantity(IItem item, int quantity);

    /**
     * Add an item quantity to the inventory
     * @param item IItem item to add
     * @param quantity int quantity of the item to add
     */
    void addItems(IItem item, int quantity);
}
