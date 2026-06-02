package it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces;

import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IStudent;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;

public interface IItem {
    /**
     * Get the name of the item
     * @return String name of the item
     */
    String getName();

    /**
     * Use the item on a target
     *
     * @param target target of the item
     * @return true if the item was used, false otherwise
     */
    boolean use(IStudent<IStudentSkill> target);
}
