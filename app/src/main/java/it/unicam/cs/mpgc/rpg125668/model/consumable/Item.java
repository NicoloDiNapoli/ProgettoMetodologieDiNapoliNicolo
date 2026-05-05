package it.unicam.cs.mpgc.rpg125668.model.consumable;

public abstract class Item {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {return name;}

}
