package it.unicam.cs.mpgc.rpg125668.model.inventory;

import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;
import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IStudent;
import it.unicam.cs.mpgc.rpg125668.model.inventory.interfaces.IInventory;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;

import java.util.Map;

public class Inventory implements IInventory {
    private int coins;
    private final Map<IItem, Integer> items;

    public Inventory(int coins, Map<IItem, Integer> items) {
        if (items == null || coins < 0)
            throw new IllegalArgumentException("Illegal arguments: items is null or coins < 0");
        this.coins = coins;
        this.items = items;
    }

    @Override
    public void applyItem(IItem item, IStudent<IStudentSkill> student) {
        if (this.hasItem(item)) {
            if(item.use(student))
                removeItem(item);
        }
    }

    @Override
    public Map<IItem, Integer> getItems() {
        return items;
    }

    @Override
    public void addItem(IItem item) {
        if (this.hasItem(item))
            items.put(item, items.get(item) + 1);
        else
            items.put(item, 1);
    }

    @Override
    public void removeItem(IItem item) {
        if (this.hasItem(item)) {
            int quantity = items.get(item);
            if (quantity > 1) {
                items.put(item, quantity - 1);
            } else {
                items.remove(item);
            }
        } else {
            throw new IllegalArgumentException("Item not found");
        }
    }

    @Override
    public boolean hasItem(IItem item) {
        return items.containsKey(item);
    }


    public int getCoins() {return this.coins;}

    public void setCoins(int coins) {
        if (coins < 0)
            throw new IllegalArgumentException("Illegal arguments: coins < 0");
        this.coins = coins;
    }

    @Override
    public void addCoins(int coins) {
        setCoins(getCoins() + coins);
    }

    @Override
    public int seeCoins() {
      return this.coins;
    }

    @Override
    public void removeCoins(int coins) {
        if (coins < 0 || coins > this.coins)
            throw new IllegalArgumentException("Illegal arguments: coins < 0 or impossible to remove coins");
        this.coins -= coins;
    }
}