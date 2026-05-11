package it.unicam.cs.mpgc.rpg125668.model.characters;
import it.unicam.cs.mpgc.rpg125668.model.inventory.Inventory;
import it.unicam.cs.mpgc.rpg125668.model.skill.Skill;
import java.io.Serializable;
import java.util.List;

public class Student extends Character implements Serializable {
    protected final int maxPreparation=100 , maxConcentration=100;
    private int level;
    private int preparation;
    private int concentration;
    private final Inventory inventory;


    public Student(String name, int life, int level, int preparation, int concentration, int maxLife, List<Skill> skills, Inventory inventory) {
        super(name,life,maxLife, skills);
        this.level = level;
        this.preparation = preparation;
        this.concentration = concentration;
        this.inventory = inventory;
    }


    //Getter
    public int getPreparation() {return this.preparation;}
    public int getConcentration() {return this.concentration;}
    public int getLevel() {return this.level;}
    public Inventory getInventory() {return this.inventory;}

    //Setter
    public void setConcentration(int concentration) {if(concentration < 0 || concentration > maxConcentration)this.concentration = maxConcentration; else {this.concentration = concentration;}}
    public void setPreparation(int preparation) {if(preparation < 0 || preparation > maxPreparation)this.preparation = maxPreparation; else { this.preparation = preparation;}}
    public void setLevel(int level) {this.level = level;}


}
