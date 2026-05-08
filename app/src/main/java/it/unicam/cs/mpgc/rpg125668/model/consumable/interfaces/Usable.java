package it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces;

import it.unicam.cs.mpgc.rpg125668.model.characters.Student;

public interface Usable {
    /**
     * Every item that implement use, do a specific action for the Student
     * @param student
     */
    void use(Student student);
}
