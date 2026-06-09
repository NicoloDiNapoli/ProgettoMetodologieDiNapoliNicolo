package it.unicam.cs.mpgc.rpg125668.model.skill;

import it.unicam.cs.mpgc.rpg125668.model.enumeration.SkillRarity;
import it.unicam.cs.mpgc.rpg125668.model.enumeration.SkillType;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.ISkill;

public abstract class Skill implements ISkill {
    private final String name;
    private final SkillRarity rarity;
    private final int damage;
    private final String description;
    private final SkillType skillType;

    public Skill(String name, SkillRarity rarity, int damage, String description, SkillType skillType) {
        if (name == null || rarity == null || damage < 0 || description == null)
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
        if (!(o instanceof Skill skill)) return false;
        return this.name.equals(skill.getName()) && this.rarity.equals(skill.getRarity());
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + this.rarity.hashCode();
    }

    public String getName() {
        return this.name;
    }

    public SkillRarity getRarity() {
        return this.rarity;
    }

    public int getDamage() {
        return this.damage;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean isHealing() {
        return this.skillType == SkillType.HEALING;
    }

    public SkillType getSkillType() {return this.skillType;}
}