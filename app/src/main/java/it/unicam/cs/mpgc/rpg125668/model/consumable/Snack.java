package it.unicam.cs.mpgc.rpg125668.model.consumable;

import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IStudent;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IPurchasable;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;

public class Snack extends Item implements IPurchasable {
    private final int increaseLife;

    public Snack(String name, int increaseLife, int price) {
        super(name, "snack", price);
        if(increaseLife < 0) throw new IllegalArgumentException("Illegal arguments: increaseLife < 0");
        this.increaseLife = increaseLife;
    }

    @Override
    public int getIncrease() {return increaseLife;}

    @Override
    public boolean use(IStudent<IStudentSkill> target) {
        if (target == null)throw new IllegalArgumentException("Target cannot be null");
        target.heal(increaseLife);
        return true;
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
