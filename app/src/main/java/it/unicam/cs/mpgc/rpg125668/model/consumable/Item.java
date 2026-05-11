package it.unicam.cs.mpgc.rpg125668.model.consumable;

import java.io.Serializable;

public abstract class Item{
    private final String name;
    protected String type;

    public Item(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {return name;}

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof Item)) return false;
        Item item = (Item) o;
        return this.name.equals(item.getName());
    }
}
