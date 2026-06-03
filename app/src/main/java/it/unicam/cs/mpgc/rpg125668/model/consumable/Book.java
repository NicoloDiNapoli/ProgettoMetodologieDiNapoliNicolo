package it.unicam.cs.mpgc.rpg125668.model.consumable;

import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IStudent;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IPurchasable;
import it.unicam.cs.mpgc.rpg125668.model.enumeration.BookRarity;

import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;

public class Book extends Item implements IPurchasable  {
    private final BookRarity rarity;

    public Book(String name, BookRarity rarity){
        if(rarity == null) throw new IllegalArgumentException("Rarity cannot be null");
        super(name, "book", rarity.getPrice());
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

    @Override
    public int getPrice() {
        return this.price;
    }
}
