package it.unicam.cs.mpgc.rpg125668.model.characters.interfaces;

import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.ISkill;

import java.util.List;

public interface ICharacter<T extends ISkill> extends Combatant {
    /**
     * Get the list of skills of the student
     * @return list of skills
     */
    List<T> getSkills();

    /**
     * Check if the student is dead
     * @return boolean true if the student is dead, false otherwise
     */
    boolean isDead();

    /**
     * Add a skill to the character
     * @param skill skill to add
     */
    void addSkill(T skill);

    /**
     * Get the maximum life of the character
     * @return int maximum life
     */
    int getMaxLife();

    /**
     * Get the name of the character
     * @return String name of the character
     */
    String getName();

    /**
     * Get the life of the character
     * @return int life of the character
     */
    int getLife();
}
