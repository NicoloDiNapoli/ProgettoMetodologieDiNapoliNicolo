package it.unicam.cs.mpgc.rpg125668.model.characters;
import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.Combatant;
import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IStudent;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;
import it.unicam.cs.mpgc.rpg125668.model.inventory.interfaces.IInventory;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.ISkill;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;

import java.io.Serializable;
import java.util.List;

public class Student extends Character<IStudentSkill> implements Serializable, IStudent<IStudentSkill> {
    protected int maxPreparation , maxConcentration;
    private final Level level;
    private int preparation;
    private int concentration;
    private final IInventory inventory;

    public Student(String name, int life, Level level, int preparation, int concentration, int maxLife, List<IStudentSkill> skills, IInventory inventory) {
        super(name,maxLife,life, skills);
        if(level == null || preparation < 0 || concentration < 0 || inventory == null) throw new IllegalArgumentException("Illegal arguments: level = null || preparation < 0 || concentration < 0 orto inventory == null");
        this.level = level;
        this.preparation = preparation;
        this.concentration = concentration;
        this.inventory = inventory;
        this.setMaxPreparation();
        this.setMaxConcentration();
        this.setMaxLife();
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
    public void useItem(IItem item){
        this.inventory.applyItem(item, this);
    }

    @Override
    public IInventory getInventory() {
        return this.inventory;
    }

    @Override
    public Level getLevel() {
        return this.level;
    }

    @Override
    public void addExperience(int experienceRewarded){
        if (experienceRewarded < 0) throw new IllegalArgumentException("Experience rewarded is negative");
        this.level.addExperience(experienceRewarded);
        setMaxConcentration();
        setMaxPreparation();
        setMaxLife();
    }

    private void setMaxPreparation() {
        this.maxPreparation =  100 + (level.getLevel() - 1) * 30;
    }

    private void setMaxConcentration() {
        this.maxConcentration =  100 + (level.getLevel() - 1) * 20;
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
    public int getMaxPreparation() {return this.maxPreparation;}

    @Override
    public int getMaxConcentration() {return this.maxConcentration;}


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
        else this.preparation = Math.max(preparation, 0);
    }

    @Override
    public void setMaxLife() {
        this.maxLife = 100 + (level.getLevel() - 1) * 10;
    }
}
