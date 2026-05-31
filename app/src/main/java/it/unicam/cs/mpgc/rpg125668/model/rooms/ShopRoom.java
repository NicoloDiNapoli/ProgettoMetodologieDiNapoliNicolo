package it.unicam.cs.mpgc.rpg125668.model.rooms;

import it.unicam.cs.mpgc.rpg125668.model.dispenser.interfaces.IShop;
import it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces.IRoom;

import java.util.List;

public class ShopRoom extends Room{

    private final IShop dispenser;

    public ShopRoom(String name, List<IRoom> exits, String description, IShop dispenser) {
        super(name, exits, description);
        if (dispenser == null) throw new IllegalArgumentException("Dispenser cannot be null");
        this.dispenser = dispenser;
    }

    public IShop getDispenser() {
        return dispenser;
    }
}
