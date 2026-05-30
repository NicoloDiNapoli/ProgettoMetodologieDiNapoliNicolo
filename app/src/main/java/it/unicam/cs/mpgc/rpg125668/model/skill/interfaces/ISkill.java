package it.unicam.cs.mpgc.rpg125668.model.skill.interfaces;

import it.unicam.cs.mpgc.rpg125668.model.enumeration.SkillRarity;
import it.unicam.cs.mpgc.rpg125668.model.enumeration.SkillType;

public interface ISkill {
    /**
     * Get the damage to the skill
     * @return int damage
     */
    int getDamage();

    /**
     * Get the name of the skill
     * @return String name
     */
    String getName();

    /**
     * Get the rarity of the skill
     * @return SkillRarity rarity
     */
    SkillRarity getRarity();

    /**
     * Get the description of the skill
     * @return String description
     */
    String getDescription();

    /**
     * Get the type of the skill
     * @return SkillType type
     */
    SkillType getSkillType();

    /**
     * Check if the skill is a healing skill
     * @return boolean true if the skill is a healing skill, false otherwise
     */
    boolean isHealing();
}
