package it.unicam.cs.mpgc.rpg125668.model.inventory.interfaces;

/**
 * Interface for the currency management of an inventory
 */
public interface ICurrencyManagement {
    /**
     * Add coins from the inventory
     * @param coins int coins to add
     */
    void addCoins(int coins);

    /**
     * See how many coins are in the inventory
     * @return int coins
     */
    int seeCoins();

    /**
     * Remove coins from the inventory
     * @param coins int coins to remove
     */
    void removeCoins(int coins);
}
