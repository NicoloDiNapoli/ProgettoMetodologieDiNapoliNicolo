package it.unicam.cs.mpgc.rpg125668.model.characters.interfaces;

import it.unicam.cs.mpgc.rpg125668.model.characters.Level;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;
import it.unicam.cs.mpgc.rpg125668.model.inventory.interfaces.IInventory;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;

import java.util.List;

/**
 * Interface for a player student character
 * @param <T> type of the skill
 */
public interface IStudent<T extends IStudentSkill> extends ICharacter<T> {
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
     * Get the max preparation of the student
     * @return int max preparation
     */
    int getMaxPreparation();

    /**
     * Get the max concentration of the student
     * @return int max concentration
     */
    int getMaxConcentration();

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
    void useItem(IItem item);

    /**
     * Get the inventory of the student
     * @return IInventory inventory
     */
    IInventory getInventory();

    /**
     * Get the level of the student
     * @return Level level
     */
    Level getLevel();
}
