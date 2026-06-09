package it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces;

/**
 * Interface for an item that can be purchased
 */
public interface IPurchasable extends IItem {
    /**
     * Get the price of the item
     * @return int price of the item
     */
    int getPrice();
}
