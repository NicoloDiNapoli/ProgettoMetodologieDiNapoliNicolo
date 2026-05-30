package it.unicam.cs.mpgc.rpg125668.model.inventory.interfaces;

import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IStudent;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.StudentUsable;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;

import java.util.Map;

public interface IInventory extends ICurrencyManagement, IItemManagement {
    /**
     * Use an item
     * @param item item to use
     */
    void useItem(StudentUsable item, IStudent<IStudentSkill> target);

    /**
     * Get the items in the inventory
     * @return Map<IItem, Integer>
     */
    Map<IItem, Integer> getItems();
}
