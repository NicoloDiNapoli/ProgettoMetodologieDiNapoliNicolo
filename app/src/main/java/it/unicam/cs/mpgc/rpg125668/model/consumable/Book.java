package it.unicam.cs.mpgc.rpg125668.model.consumable;

import it.unicam.cs.mpgc.rpg125668.model.characters.Student;
import it.unicam.cs.mpgc.rpg125668.model.enumeration.BookRarity;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.Usable;

public class Book extends Item implements Usable{
    private final BookRarity rarity;

    public Book(String name, BookRarity rarity, String type){
        super(name, type);
        this.rarity = rarity;
    }

    @Override
    public void use(Student student) {
        student.setPreparation(student.getPreparation() + rarity.getPreparation());
    }
}
