package it.unicam.cs.mpgc.rpg125668.model.consumable;

import it.unicam.cs.mpgc.rpg125668.model.characters.Student;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.Usable;

public class Snack extends Item implements Usable {
    private int increaseLife;

    public Snack(String name, int increaseLife) {
        super(name);
        this.increaseLife = increaseLife;
    }

    @Override
    public void use(Student student) {
        student.heal(increaseLife);
    }

    public int getIncreaseLife() {return increaseLife;}
}
