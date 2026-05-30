package it.unicam.cs.mpgc.rpg125668.model.rooms;

import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;
import it.unicam.cs.mpgc.rpg125668.model.inventory.Inventory;
import it.unicam.cs.mpgc.rpg125668.model.inventory.interfaces.IInventory;
import it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces.IRoom;
import it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces.Lootable;

import java.util.HashMap;
import java.util.List;

public class LootableRoom extends Room implements Lootable {
    private IInventory roomInventory;
    private int coinsLotable;


    public LootableRoom(String name, List<IRoom> exits, String description, IInventory roomInventory, int coinsLotable) {
        super(name, exits, description);
        if(roomInventory == null || coinsLotable < 0) throw new IllegalArgumentException("Illegal arguments: itemsLotable is null or coinsLotable < 0");
        this.roomInventory = roomInventory;
        this.coinsLotable = coinsLotable;
    }

    public int getLootableCoins() {
        if(coinsLotable < 0) throw new IllegalArgumentException("Illegal arguments: coinsLotable < 0");
        return coinsLotable;
    }

    public void setLootableCoins(int coinsLotable) {
        if(coinsLotable < 0) throw new IllegalArgumentException("Illegal arguments: coinsLotable < 0");
        this.coinsLotable = coinsLotable;
    }

    @Override
    public void addCoins(int coins) {
        if(coins < 0) throw new IllegalArgumentException("Illegal arguments: coins < 0");
        this.setLootableCoins(this.getLootableCoins()+coins);
    }

    @Override
    public void removeCoins(int coins) {
        if (this.coinsLotable < coins || coins < 0) throw new IllegalArgumentException("Illegal arguments: coins < 0 or impossible to remove coins");
        this.setLootableCoins(this.getLootableCoins()-coins);
    }


    @Override
    public void addItem(IItem item) {
        if(item == null) throw new IllegalArgumentException("Illegal arguments: item is null");
        roomInventory.addItem(item);
    }

    @Override
    public void removeItem(IItem item) {
        if (item == null) throw new IllegalArgumentException("Illegal arguments: item is null");
        roomInventory.removeItem(item);
    }

    @Override
    public boolean hasItem(IItem item) {
        return roomInventory.hasItem(item);
    }

    @Override
    public int getItemQuantity(IItem item) {
        return roomInventory.getItemQuantity(item);
    }

    @Override
    public void removeItemQuantity(IItem item, int quantity) {
        if(quantity < 0) throw new IllegalArgumentException("Illegal arguments: quantity < 0");
        roomInventory.removeItemQuantity(item, quantity);
    }

    @Override
    public void addItems(IItem item, int quantity) {
        if(quantity < 0 || item == null) throw new IllegalArgumentException("Illegal arguments: quantity < 0 or item is null");
        this.roomInventory.addItems(item, quantity);
    }

    @Override
    public IInventory loot() {
        IInventory loot = this.roomInventory;
        this.roomInventory = this.reset();
        return loot;
    }

    @Override
    public IInventory reset() {
        return new Inventory(0, new HashMap<>());
    }
}
