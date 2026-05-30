package it.unicam.cs.mpgc.rpg125668.model.consumable;

import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IStudent;
import it.unicam.cs.mpgc.rpg125668.model.enumeration.BookRarity;

import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;

public class Book extends Item{
    private final BookRarity rarity;

    public Book(String name, BookRarity rarity, String type){
        super(name, type);
        if(rarity == null) throw new IllegalArgumentException("Rarity cannot be null");
        this.rarity = rarity;
    }

    public String toString() {
        return this.getName();
    }

    @Override
    public boolean use(IStudent<IStudentSkill> target) {
        if (target == null)throw new IllegalArgumentException("Target cannot be null");
        if (target.getConcentration() < this.rarity.getConcentrationRequired()) return false;
        target.setPreparation(target.getPreparation() + this.rarity.getPreparation());
        target.setConcentration(target.getConcentration() - this.rarity.getConcentrationRequired());
        return true;
    }
}
