package it.unicam.cs.mpgc.rpg125668.model.skill;

import it.unicam.cs.mpgc.rpg125668.model.characters.Character;
import it.unicam.cs.mpgc.rpg125668.model.enumeration.SkillRarity;

public  class Skill {
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

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof Skill)) return false;
        Skill skill = (Skill) o;
        return this.name.equals(skill.getName()) && this.rarity.equals(skill.rarity);
    }

    public String getName() {return name;}
    public int getPreparationRequired() {return preparationRequired;}
}
