package it.unicam.cs.mpgc.rpg125668.model.consumable;

import it.unicam.cs.mpgc.rpg125668.model.characters.Student;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.Usable;

import java.io.Serializable;

public class Snack extends Item implements Usable {
    private final int increaseLife;

    public Snack(String name, int increaseLife, String type) {
        super(name, type);
        this.increaseLife = increaseLife;
    }

    @Override
    public void use(Student student) {
        student.heal(increaseLife);
    }

    public int getIncreaseLife() {return increaseLife;}
}
