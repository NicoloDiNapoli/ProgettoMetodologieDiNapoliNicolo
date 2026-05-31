package it.unicam.cs.mpgc.rpg125668.model.dispenser;

import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;

import java.util.Map;

public class SnackDrinkDispenser extends Dispenser{

    public SnackDrinkDispenser(Map<IItem, Integer> items) {
        super("Snack & Drink dispenser", items);
    }
}


