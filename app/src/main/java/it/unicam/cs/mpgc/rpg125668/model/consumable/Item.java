package it.unicam.cs.mpgc.rpg125668.model.consumable;

<<<<<<< Updated upstream
import java.io.Serializable;

public abstract class Item{
=======
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.StudentUsable;


public abstract class Item implements IItem, StudentUsable {
>>>>>>> Stashed changes
    private final String name;
    private final String type;
    public Item(String name, String type) {
<<<<<<< Updated upstream
=======
        if(name == null) throw new IllegalArgumentException("Name cannot be null");
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
=======

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
>>>>>>> Stashed changes
}
