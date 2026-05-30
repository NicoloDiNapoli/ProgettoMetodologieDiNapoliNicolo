package it.unicam.cs.mpgc.rpg125668.model.characters;
import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.Combatant;
import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IStudent;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.StudentUsable;
import it.unicam.cs.mpgc.rpg125668.model.inventory.interfaces.IInventory;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.ISkill;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;

import java.io.Serializable;
import java.util.List;

public class Student extends Character<IStudentSkill> implements Serializable, Combatant, IStudent<IStudentSkill> {
    protected final int maxPreparation=100 , maxConcentration=100;
    private int level;
    private int preparation;
    private int concentration;
    private final IInventory inventory;

    public Student(String name, int life, int level, int preparation, int concentration, int maxLife, List<IStudentSkill> skills, IInventory inventory) {
        super(name,maxLife,life, skills);
        if(level < 0 || preparation < 0 || concentration < 0 || inventory == null) throw new IllegalArgumentException("Illegal arguments: level < 0 || preparation < 0 || concentration < 0 orto inventory == null");
        this.level = level;
        this.preparation = preparation;
        this.concentration = concentration;
        this.inventory = inventory;
    }

    @Override
    public void useSkill(Combatant target, ISkill skill) {
        if(target == null || skill == null) throw new IllegalArgumentException("Illegal arguments: target or skill is null");
        if(skill.isHealing())
            target.heal(skill.getDamage());
        else
            target.takeDamage(skill.getDamage());
    }

    @Override
    public void heal(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Healing amount is negative");
        this.setLife(this.getLife() + amount);
    }

    @Override
    public void takeDamage(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Damage amount is negative");
        this.setLife(this.getLife() - amount);
    }

    @Override
    public void useItem(StudentUsable item){
        if(item != null)
            this.inventory.useItem(item, this);
    }

    public IInventory getInventory() {
        return this.inventory;
    }


    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int getPreparation() {
        return this.preparation;
    }
    @Override
    public int getConcentration() {
        return this.concentration;
    }


    @Override
    public void setConcentration(int concentration) {
        if(concentration > maxConcentration)
            this.concentration = maxConcentration;
        else if(concentration < 0)
            this.concentration = 0;
        else this.concentration = concentration;
    }

    @Override
    public void setPreparation(int preparation) {
        if(preparation > maxPreparation)
            this.preparation = maxPreparation;
        else if(preparation < 0 )
            this.preparation = 0;
        else this.preparation = preparation;
    }
}
