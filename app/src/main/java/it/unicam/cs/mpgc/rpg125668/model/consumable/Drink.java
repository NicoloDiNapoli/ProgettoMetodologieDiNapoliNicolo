package it.unicam.cs.mpgc.rpg125668.model.consumable;
import it.unicam.cs.mpgc.rpg125668.model.characters.Student;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.Usable;

public class Drink extends Item implements Usable{
    private final int increaseConcentration;

    public Drink(String name, int increaseConcentration, String type) {
        super(name, type);
        this.increaseConcentration = increaseConcentration;
    }

    @Override
    public void use(Student student) {
        student.setConcentration(student.getConcentration()+increaseConcentration);
    }

    public int getIncreaseConcentration() {return increaseConcentration;}
}
