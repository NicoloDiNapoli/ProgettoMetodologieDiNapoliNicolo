package it.unicam.cs.mpgc.rpg125668.model.skill;

import it.unicam.cs.mpgc.rpg125668.model.enumeration.SkillRarity;
import it.unicam.cs.mpgc.rpg125668.model.enumeration.SkillType;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.ISkill;

public class Skill implements ISkill {
    private final String name;
    private final SkillRarity rarity;
    private final int damage;
    private final String description;
    private final SkillType skillType;

    public Skill(String name, SkillRarity rarity, int damage, String description, SkillType skillType) {
        if (name == null || rarity == null || damage < 0 || description == null || skillType == null)
            throw new IllegalArgumentException("Illegal arguments: null values or negative damage");
        this.name = name;
        this.rarity = rarity;
        this.damage = damage;
        this.description = description;
        this.skillType = skillType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Skill)) return false;
        Skill skill = (Skill) o;
        return this.name.equals(skill.getName()) && this.rarity.equals(skill.getRarity());
    }

    public String getName() {
        return name;
    }

    public SkillRarity getRarity() {
        return rarity;
    }

    public int getDamage() {
        return damage;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public SkillType getSkillType() {
        return this.skillType;
    }

    public boolean isHealing() {
        return skillType == SkillType.HEALING;
    }
}