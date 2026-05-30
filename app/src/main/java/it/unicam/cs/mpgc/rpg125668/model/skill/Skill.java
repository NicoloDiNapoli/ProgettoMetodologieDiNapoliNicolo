package it.unicam.cs.mpgc.rpg125668.model.skill;
import it.unicam.cs.mpgc.rpg125668.model.enumeration.SkillRarity;
import it.unicam.cs.mpgc.rpg125668.model.enumeration.SkillType;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.ISkill;

<<<<<<< Updated upstream
public  class Skill {
    private String name;
    private int preparationRequired;
    private SkillRarity rarity;
    private int damage;

    public Skill(String name, int preparationRequired, SkillRarity rarity, int damage) {
=======
public  class Skill implements ISkill {
    private final String name;
    private final SkillRarity rarity;
    private final int damage;
    private final String description;
    private final SkillType skillType;

    public Skill(String name, SkillRarity rarity, int damage, String description, SkillType skillType) {
        if(name == null || rarity == null || damage < 0 || description == null) throw new IllegalArgumentException("Illegal arguments: name is null or preparationRequired < 0 or rarity is null or damage < 0 or description is null");
>>>>>>> Stashed changes
        this.name = name;
        this.rarity = rarity;
        this.damage = damage;
<<<<<<< Updated upstream
=======
        this.description = description;
        this.skillType = skillType;
>>>>>>> Stashed changes
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof Skill)) return false;
        Skill skill = (Skill) o;
        return this.name.equals(skill.getName()) && this.rarity.equals(skill.rarity);
    }

    public String getName() {return name;}
<<<<<<< Updated upstream
    public int getPreparationRequired() {return preparationRequired;}
=======
    public SkillRarity getRarity() {return rarity;}
    public int getDamage() {return damage;}
    public String getDescription() {return description;}

    @Override
    public SkillType getSkillType() {return this.skillType;}

    public boolean isHealing() {
        return skillType == SkillType.HEALING;
    }
>>>>>>> Stashed changes
}
