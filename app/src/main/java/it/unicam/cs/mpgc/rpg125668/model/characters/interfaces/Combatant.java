package it.unicam.cs.mpgc.rpg125668.model.characters.interfaces;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.ISkill;

/**
 * Interface for all the characters that can be combatted
 */
public interface Combatant {
    /**
     * Use a skill on a target
     * @param target target of the skill
     * @param skill skill to use
     */
    void useSkill(Combatant target, ISkill skill);

    /**
     * Heal a target
     * @param amount >0
     */
    void heal(int amount);

    /**
     * Damage a target
     * @param amount >0
     */
    void takeDamage(int amount);
}
