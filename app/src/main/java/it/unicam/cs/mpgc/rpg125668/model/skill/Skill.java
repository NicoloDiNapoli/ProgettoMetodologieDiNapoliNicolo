package it.unicam.cs.mpgc.rpg125668.model.skill;

import it.unicam.cs.mpgc.rpg125668.model.characters.Character;
import it.unicam.cs.mpgc.rpg125668.model.enumeration.SkillRarity;

public abstract class Skill {
    private String name;
    private int preparationRequired;
    private SkillRarity rarity;

    public Skill(String name, int preparationRequired, SkillRarity rarity) {
        this.name = name;
        this.preparationRequired = preparationRequired;
        this.rarity = rarity;
    }

    public abstract void attack(Character target);

    public String getName() {return name;}
    public int getPreparationRequired() {return preparationRequired;}
}
