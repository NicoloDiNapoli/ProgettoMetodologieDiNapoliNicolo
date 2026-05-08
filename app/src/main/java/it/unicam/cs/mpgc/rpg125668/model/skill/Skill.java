package it.unicam.cs.mpgc.rpg125668.model.skill;

import it.unicam.cs.mpgc.rpg125668.model.characters.Character;
import it.unicam.cs.mpgc.rpg125668.model.enumeration.SkillRarity;

public abstract class Skill {
    private String name;
    private int preparationRequired;
    private SkillRarity rarity;
    private int damage;

    public Skill(String name, int preparationRequired, SkillRarity rarity, int damage) {
        this.name = name;
        this.preparationRequired = preparationRequired;
        this.rarity = rarity;
        this.damage = damage;
    }

    public String getName() {return name;}
    public int getPreparationRequired() {return preparationRequired;}
}
