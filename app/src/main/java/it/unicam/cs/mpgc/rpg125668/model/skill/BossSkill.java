package it.unicam.cs.mpgc.rpg125668.model.skill;

import it.unicam.cs.mpgc.rpg125668.model.enumeration.SkillRarity;
import it.unicam.cs.mpgc.rpg125668.model.enumeration.SkillType;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IBossSkill;

public class BossSkill extends Skill implements IBossSkill {

    public BossSkill(String name, SkillRarity rarity, int damage, String description, SkillType skillType) {
        super(name, rarity, damage, description, skillType);
    }
}
