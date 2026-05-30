package it.unicam.cs.mpgc.rpg125668.model.consumable;
import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IStudent;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;


public class Drink extends Item{
    private final int increaseConcentration;

    public Drink(String name, int increaseConcentration, String type) {
        super(name, type);
        if(increaseConcentration < 0) throw new IllegalArgumentException("Illegal arguments: increaseConcentration < 0");
        this.increaseConcentration = increaseConcentration;
    }

    public int getIncreaseConcentration() {return increaseConcentration;}

    @Override
    public boolean use(IStudent<IStudentSkill> target) {
        if(target == null) throw new IllegalArgumentException("Target cannot be null");
        target.setConcentration(target.getConcentration()+increaseConcentration);
        return true;
    }
}
