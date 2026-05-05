package it.unicam.cs.mpgc.rpg125668.model.consumable;

import it.unicam.cs.mpgc.rpg125668.model.characters.Student;
import it.unicam.cs.mpgc.rpg125668.model.enumeration.BookRarity;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.Usable;

public class Book extends Item implements Usable{
    private String name;
    private BookRarity rarity;

    public Book(String name, BookRarity rarity){
        super(name);
        this.rarity = rarity;
    }

    @Override
    public void use(Student student) {
        student.setPreparation(student.getPreparation() + rarity.getPreparation());
    }


    private void setRarity(BookRarity rarity) {if(rarity == null)throw new NullPointerException("Rarity is null"); this.rarity = rarity;}
}
