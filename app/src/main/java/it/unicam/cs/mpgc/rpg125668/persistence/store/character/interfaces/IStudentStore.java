package it.unicam.cs.mpgc.rpg125668.persistence.store.character.interfaces;


import it.unicam.cs.mpgc.rpg125668.persistence.entity.character.StudentEntity;

/**
 * Interface for Student Query
 */
public interface IStudentStore {
    /**
     * Find a student by name
     * @param name String name of the student
     * @return StudentEntity student
     */
    StudentEntity findByName(String name);
}
