package it.unicam.cs.mpgc.rpg125668.model.inventory;
import it.unicam.cs.mpgc.rpg125668.model.consumable.Book;
import it.unicam.cs.mpgc.rpg125668.model.consumable.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private int coins;
    private Map<Item, Integer> items;

    public Inventory() {
        this.coins = 0;
        this.items = new HashMap<>();
    }

    public Inventory(int coins, List<Book> books, Map<Item, Integer> items) {
        this.coins = coins;
        this.items = items;
    }

    public boolean useItem(Item item) {
        Integer quantity = items.get(item);

        if (quantity == null || quantity <= 0) {
            return false;
        }
        if (quantity == 1) {
            items.remove(item);
        } else {
            items.put(item, quantity - 1);
        }
        return true;
    }

    public void addCoins(int coins) {this.coins += coins;}
    public void addItem(Item item, int quantity) {this.items.put(item, quantity);}

    public int getCoins() {return coins;}
    public Map<Item, Integer> getItems() {return items;}
}
