package it.unicam.cs.mpgc.rpg125668.model.consumable;


import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.StudentUsable;

public abstract class Item implements IItem, StudentUsable {
    private final String name;
    private final String type;
    public Item(String name, String type) {
        if(name == null || type == null) throw new IllegalArgumentException("Name cannot be null or type cannot be null");
        this.name = name;
        this.type = type;
    }

    public String getName() {return name;}
    public String getType() {return type;}

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof Item item)) return false;
        return this.name.equals(item.getName());
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

}
