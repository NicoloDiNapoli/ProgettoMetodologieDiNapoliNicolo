package it.unicam.cs.mpgc.rpg125668.model.inventory.interfaces;

public interface ICurrencyManagement {
    /**
     * Add coins from the inventory
     * @param coins int coins to add
     */
    void addCoins(int coins);

    /**
     * Remove coins from the inventory
     * @param coins int coins to remove
     */
    void removeCoins(int coins);
}
