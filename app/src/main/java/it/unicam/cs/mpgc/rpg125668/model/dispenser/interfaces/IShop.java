package it.unicam.cs.mpgc.rpg125668.model.dispenser.interfaces;

import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IStudent;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IPurchasable;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;


import java.util.Map;

public interface IShop {
    /**
     * Get the name of the shop
     * @return String name of the shop
     */
    String getName();

    /**
     * Get the map of items of the shop
     * @return Map<IItem, Integer> list of items
     */
    Map<IItem, Integer> getItems();

    /**
     * Buy an item from the shop
     * @param student student who buys the item
     * @param item IPurchasable extends IItem item to buy
     */
    void buyItem(IStudent<IStudentSkill> student, IPurchasable item);

    /**
     * Restock the shop
     * @param amount of items to restock
     */
    void restockItems(int amount);
}
