package it.unicam.cs.mpgc.rpg125668.model.inventory;

import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.StudentUsable;
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
    public void useItem(StudentUsable item, IStudent<IStudentSkill> student) {
        if (this.hasItem(item)) {
            item.use(student);
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

    @Override
    public int getItemQuantity(IItem item) {
        return items.getOrDefault(item, 0);
    }

    @Override
    public void removeItemQuantity(IItem item, int quantity) {
        if (quantity < 0)
            throw new IllegalArgumentException("Illegal arguments: quantity < 0");
        if (hasItem(item)) {
            int currentQty = items.get(item);
            if (currentQty >= quantity) {
                if (currentQty == quantity) {
                    items.remove(item);
                } else {
                    items.put(item, currentQty - quantity);
                }
            } else {
                throw new IllegalArgumentException("Impossible to remove quantity");
            }
        } else {
            throw new IllegalArgumentException("Item not found");
        }
    }

    @Override
    public void addItems(IItem item, int quantity) {
        if (quantity < 0 || item == null)
            throw new IllegalArgumentException("Illegal arguments: quantity < 0 or item is null");
        items.put(item, items.getOrDefault(item, 0) + quantity);
    }

    @Override
    public int getCoins() {
        return coins;
    }

    @Override
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
    public void removeCoins(int coins) {
        if (coins < 0 || coins > this.coins)
            throw new IllegalArgumentException("Illegal arguments: coins < 0 or impossible to remove coins");
        this.coins -= coins;
    }
}