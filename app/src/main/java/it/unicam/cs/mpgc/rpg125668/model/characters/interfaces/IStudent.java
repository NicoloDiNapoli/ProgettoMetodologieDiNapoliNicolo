package it.unicam.cs.mpgc.rpg125668.model.characters.interfaces;

import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.StudentUsable;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;

import java.util.List;

public interface IStudent<T extends IStudentSkill> extends ICharacter<T>,Combatant {
    /**
     * Get the preparation of the student
     * @return int preparation
     */
    int getPreparation();
    /**
     * Get the concentration of the student
     * @return int concentration
     */
    int getConcentration();

    /**
     * Set the preparation of the student
     * @param preparation >0
     */
    void setPreparation(int preparation);
    /**
     * Set the concentration of the student
     * @param  concentration >0
     */
    void setConcentration(int concentration);

    /**
     * Use an item
     * @param item item to use
     *
     */
    void useItem(StudentUsable item);
}
