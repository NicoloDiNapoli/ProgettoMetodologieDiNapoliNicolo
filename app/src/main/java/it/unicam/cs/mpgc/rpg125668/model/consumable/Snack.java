package it.unicam.cs.mpgc.rpg125668.model.consumable;
<<<<<<< Updated upstream

import it.unicam.cs.mpgc.rpg125668.model.characters.Student;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.Usable;

import java.io.Serializable;
=======
import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IStudent;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;
>>>>>>> Stashed changes

public class Snack extends Item implements Usable {
    private final int increaseLife;

    public Snack(String name, int increaseLife, String type) {
        super(name, type);
        this.increaseLife = increaseLife;
    }

    @Override
<<<<<<< Updated upstream
    public void use(Student student) {
        student.heal(increaseLife);
=======
    public boolean use(IStudent<IStudentSkill> target) {
        if (target == null)throw new IllegalArgumentException("Target cannot be null");
        target.heal(increaseLife);
        return true;
>>>>>>> Stashed changes
    }
}
