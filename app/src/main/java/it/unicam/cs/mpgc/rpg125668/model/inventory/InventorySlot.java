package it.unicam.cs.mpgc.rpg125668.model.inventory;

import it.unicam.cs.mpgc.rpg125668.model.consumable.Item;

public class InventorySlot {
    private final Item item;
    private int quantity;

    public InventorySlot(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {return item;}
    public int getQuantity() {return quantity;}

    public void setQuantity(int quantity) {this.quantity = quantity;}
}
