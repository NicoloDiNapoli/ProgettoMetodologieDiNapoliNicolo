package it.unicam.cs.mpgc.rpg125668.persistence.entity.skill;

import it.unicam.cs.mpgc.rpg125668.model.enumeration.SkillRarity;
import it.unicam.cs.mpgc.rpg125668.model.enumeration.SkillType;
import jakarta.persistence.*;

@MappedSuperclass
public abstract class SkillEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "rarity", nullable = false)
    private SkillRarity rarity;

    @Column(name = "damage", nullable = false)
    private int damage;

    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "skill_type", nullable = false)
    private SkillType skillType;

    protected SkillEntity() {}

    public SkillEntity(String name, SkillRarity rarity, int damage, String description, SkillType skillType) {
        this.name = name;
        this.rarity = rarity;
        this.damage = damage;
        this.description = description;
        this.skillType = skillType;
    }

    public String getName() { return name; }
    public SkillRarity getRarity() { return rarity; }
    public int getDamage() { return damage; }
    public String getDescription() { return description; }
    public SkillType getSkillType() { return skillType; }
}