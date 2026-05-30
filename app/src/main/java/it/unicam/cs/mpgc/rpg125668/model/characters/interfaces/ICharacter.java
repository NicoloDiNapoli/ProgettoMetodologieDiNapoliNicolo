package it.unicam.cs.mpgc.rpg125668.model.characters.interfaces;

import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.ISkill;

import java.util.List;

public interface ICharacter<T extends ISkill> {
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
     * Get the max life of the student
     * @return int max life
     */
    int getMaxLife();
}
