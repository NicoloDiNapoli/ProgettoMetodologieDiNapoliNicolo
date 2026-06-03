package it.unicam.cs.mpgc.rpg125668.model.rooms;

import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;
import it.unicam.cs.mpgc.rpg125668.model.inventory.Inventory;
import it.unicam.cs.mpgc.rpg125668.model.inventory.interfaces.IInventory;
import it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces.IRoom;
import it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces.IRoomLootable;

import java.util.HashMap;
import java.util.List;

public class LootableRoom extends Room implements IRoomLootable {
    private IInventory roomInventory;

    public LootableRoom(String name, List<IRoom> exits, String description, IInventory roomInventory) {
        super(name, exits, description);
        if(roomInventory == null) throw new IllegalArgumentException("Illegal arguments: itemsLotable is null or coinsLotable < 0");
        this.roomInventory = roomInventory;
    }

    public int getLootableCoins() {
        return this.roomInventory.seeCoins();
    }

    public void setLootableCoins(int coinsLotable) {
        if(coinsLotable < 0) throw new IllegalArgumentException("Illegal arguments: coinsLotable < 0");
        this.roomInventory.addCoins(coinsLotable);
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

    @Override
    public void generateLoot(List<IItem> items, int coins) {
        items.forEach(item -> this.roomInventory.addItem(item));
        this.roomInventory.addCoins(coins);
    }
}
