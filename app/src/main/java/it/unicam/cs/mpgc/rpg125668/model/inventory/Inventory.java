package it.unicam.cs.mpgc.rpg125668.model.inventory;
import it.unicam.cs.mpgc.rpg125668.model.consumable.Item;

import java.util.ArrayList;
import java.util.List;


public class Inventory {
    private int coins;
    private List<InventorySlot> items;

    public Inventory(int coins,List<InventorySlot> items) {
        this.coins = coins;
        this.items = items;
    }

    public boolean useItem(Item item) {
        for(InventorySlot slot : items){
            if(slot.getItem().equals(item)){
                slot.setQuantity(slot.getQuantity()-1);
                return true;
            }
        }
        return false;
    }

    public void addCoins(int coins) {this.coins += coins;}

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
}
