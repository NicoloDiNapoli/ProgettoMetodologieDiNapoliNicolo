package it.unicam.cs.mpgc.rpg125668.model.dispenser;

import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;

import java.util.Map;

public class BookDispenser extends Dispenser{
    public BookDispenser(Map<IItem, Integer> items){
        super("Book dispenser", items);
    }
}
