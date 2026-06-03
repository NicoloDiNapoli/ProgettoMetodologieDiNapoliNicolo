package it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces;

import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;
import it.unicam.cs.mpgc.rpg125668.model.dispenser.interfaces.IShop;

import java.util.Map;

public interface IShopRoom extends IRoom{
    /**
     * Get the dispenser of the shop
     * @return IShop dispenser of the shop
     */
    IShop getDispenser();
}
