package it.unicam.cs.mpgc.rpg125668.model.consumable;
import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IStudent;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IPurchasable;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;


public class Drink extends Item implements IPurchasable {
    private final int increaseConcentration;

    public Drink(String name, int increaseConcentration, int price) {
        super(name, "drink", price);
        if(increaseConcentration < 0) throw new IllegalArgumentException("Illegal arguments: increaseConcentration < 0");
        this.increaseConcentration = increaseConcentration;
    }

    @Override
    public int getIncrease() {return increaseConcentration;}

    @Override
    public boolean use(IStudent<IStudentSkill> target) {
        if(target == null) throw new IllegalArgumentException("Target cannot be null");
        target.setConcentration(target.getConcentration()+increaseConcentration);
        return true;
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
