package it.unicam.cs.mpgc.rpg125668.model.characters;

import it.unicam.cs.mpgc.rpg125668.model.inventory.Inventory;
import it.unicam.cs.mpgc.rpg125668.model.skill.Skill;

import java.util.List;

public class Student extends Character {
    protected final int maxPreparation = 100,maxConcentration = 100;
    private int level;
    private int preparation;
    private int concentration;
    private Inventory inventory;
    private List<Skill> skills;

    public Student(String name, int life, int level, int preparation, int concentration, int maxLife, List<Skill> skills) {
        super(name,life,maxLife);
        this.level = level;
        this.preparation = preparation;
        this.concentration = concentration;
        this.skills = skills;
    }

    //Getter
    public int getPreparation() {return this.preparation;}
    public int getConcentration() {return this.concentration;}
    public int getLevel() {return this.level;}

    //Setter
    public void setConcentration(int concentration) {if(concentration < 0 || concentration > maxConcentration)this.concentration = maxConcentration; else {this.concentration = concentration;}}
    public void setPreparation(int preparation) {if(preparation < 0 || preparation > maxPreparation)this.preparation = maxPreparation; else { this.preparation = preparation;}}
    public void setLevel(int level) {if(level <0)throw new IllegalArgumentException("Level not valid"); this.level = level;}


}
