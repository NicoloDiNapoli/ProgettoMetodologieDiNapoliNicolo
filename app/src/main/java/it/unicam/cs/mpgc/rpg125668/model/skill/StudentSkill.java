package it.unicam.cs.mpgc.rpg125668.model.skill;

import it.unicam.cs.mpgc.rpg125668.model.enumeration.SkillRarity;
import it.unicam.cs.mpgc.rpg125668.model.enumeration.SkillType;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;

public class StudentSkill extends Skill implements IStudentSkill {
    private final int preparationRequired;

    public StudentSkill(int preparationRequired, String name, SkillRarity rarity, int damage, String description, SkillType skilType) {
        super(name, rarity, damage, description, skilType);
        if(preparationRequired < 0) throw new IllegalArgumentException("Preparation required cannot be negative");
        this.preparationRequired = preparationRequired;
    }
    @Override
    public int getPreparationRequired() {return this.preparationRequired;}

}
