package it.unicam.cs.mpgc.rpg125668.model.skill.interfaces;

import it.unicam.cs.mpgc.rpg125668.model.enumeration.SkillRarity;
import it.unicam.cs.mpgc.rpg125668.model.enumeration.SkillType;

public interface ISkill {
    /**
     * Get the name of the skill
     * @return String name of the skill
     */
    String getName();

    /**
     * Get the damage to the skill
     * @return int damage
     */
    int getDamage();

    /**
     * Get the rarity of the skill
     * @return SkillRarity rarity
     */
    boolean isHealing();

    /**
     * Get the rarity of the skill
     * @return SkillRarity rarity
     */
    SkillRarity getRarity();
}
