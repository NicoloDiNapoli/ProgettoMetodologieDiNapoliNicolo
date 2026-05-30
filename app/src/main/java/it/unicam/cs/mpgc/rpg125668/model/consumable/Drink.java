package it.unicam.cs.mpgc.rpg125668.model.consumable;
<<<<<<< Updated upstream
import it.unicam.cs.mpgc.rpg125668.model.characters.Student;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.Usable;
=======
import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IStudent;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;
>>>>>>> Stashed changes

public class Drink extends Item implements Usable{
    private final int increaseConcentration;

    public Drink(String name, int increaseConcentration, String type) {
        super(name, type);
        this.increaseConcentration = increaseConcentration;
    }

<<<<<<< Updated upstream
    @Override
    public void use(Student student) {
        student.setConcentration(student.getConcentration()+increaseConcentration);
    }

=======
>>>>>>> Stashed changes
    public int getIncreaseConcentration() {return increaseConcentration;}

    @Override
    public boolean use(IStudent<IStudentSkill> target) {
        if(target == null) throw new IllegalArgumentException("Target cannot be null");
        target.setConcentration(target.getConcentration()+increaseConcentration);
        return true;
    }
}
