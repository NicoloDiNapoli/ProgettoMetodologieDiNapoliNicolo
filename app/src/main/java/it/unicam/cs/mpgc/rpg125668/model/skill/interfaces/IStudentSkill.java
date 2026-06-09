package it.unicam.cs.mpgc.rpg125668.model.skill.interfaces;

/**
 * Interface for a student skill that needs preparation to use it
 *
 */
public interface IStudentSkill extends ISkill{
    /**
     * Get the preparation required to use the skill
     * @return int preparation required
     */
    int getPreparationRequired();
}
