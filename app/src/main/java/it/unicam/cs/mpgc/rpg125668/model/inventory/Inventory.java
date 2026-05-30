package it.unicam.cs.mpgc.rpg125668.model.inventory;
<<<<<<< Updated upstream
import it.unicam.cs.mpgc.rpg125668.model.consumable.Item;

import java.util.ArrayList;
import java.util.List;
=======
import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IStudent;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.StudentUsable;
import it.unicam.cs.mpgc.rpg125668.model.inventory.interfaces.IInventory;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;

import java.util.Map;
>>>>>>> Stashed changes


public class Inventory implements IInventory {
    private int coins;
<<<<<<< Updated upstream
    private List<InventorySlot> items;

    public Inventory(int coins,List<InventorySlot> items) {
=======
    private final Map<IItem, Integer> items;

    public Inventory(int coins,Map<IItem, Integer> items) {
        if(items == null || coins < 0) throw new IllegalArgumentException("Illegal arguments: items is null or coins < 0");
>>>>>>> Stashed changes
        this.coins = coins;
        this.items = items;
    }

<<<<<<< Updated upstream
    public boolean useItem(Item item) {
        for(InventorySlot slot : items){
            if(slot.getItem().equals(item)){
                slot.setQuantity(slot.getQuantity()-1);
                return true;
            }
        }
        return false;
=======
    public void useItem(StudentUsable item, IStudent<IStudentSkill> student) {
        if(this.hasItem(item)) {
            item.use(student);
            items.put(item, items.get(item) - 1);
            if(items.get(item) == 0) items.remove(item);
        }
>>>>>>> Stashed changes
    }

    public Map<IItem, Integer> getItems() {return items;}

<<<<<<< Updated upstream
    public void addItem(Item item, int quantity) {
        for(InventorySlot slot : items){
            if(slot.getItem().equals(item)){
                slot.setQuantity(slot.getQuantity()+quantity);
                return;
            }
        }
        this.items.add(new InventorySlot(item, quantity));
    }

    public int getCoins() {return coins;}
    public List<InventorySlot> getItems() {return items;}
=======
    public void setCoins(int coins) {
        if(coins < 0) throw new IllegalArgumentException("Illegal arguments: coins < 0");
        this.coins = coins;
    }

    public int getCoins() {return this.coins;}

    public void addCoins(int coins) {
        setCoins(getCoins() + coins);
    }

    @Override
    public void removeCoins(int coins) {
        if(coins < 0 || coins > this.coins) throw new IllegalArgumentException("Illegal arguments: coins < 0 or impossible to remove coins");
        this.coins -= coins;
    }
    @Override
    public void addItem(IItem item) {
        if (this.hasItem(item)) items.put(item, items.get(item)+1);
        else items.put(item, 1);
    }

    @Override
    public void removeItem(IItem item) {
        if(this.hasItem(item)) {
            items.put(item, items.get(item) - 1);
            if (items.get(item) == 0) items.remove(item);
        }
        else throw new IllegalArgumentException("Item not found");
    }

    @Override
    public boolean hasItem(IItem item) {
        return items.containsKey(item);
    }

    @Override
    public int getItemQuantity(IItem item) {
        return items.entrySet().stream()
                .filter(entry -> entry.getKey().equals(item))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(0);
    }

    @Override
    public void removeItemQuantity(IItem item, int quantity) {
        if(quantity < 0) throw new IllegalArgumentException("Illegal arguments: quantity < 0");
        if(this.hasItem(item))
            if(items.get(item) >= quantity)
                items.put(item, items.get(item)-quantity);
            else throw new IllegalArgumentException("Impossible to remove quantity");
        else throw new IllegalArgumentException("Item not found");
        if(items.get(item) == 0) items.remove(item);
    }

    @Override
    public void addItems(IItem item, int quantity) {
        if(quantity < 0 || item == null) throw new IllegalArgumentException("Illegal arguments: quantity < 0 or item is null");
        this.items.put(item, this.items.getOrDefault(item, 0) + quantity    );
    }
>>>>>>> Stashed changes
}
