package it.unicam.cs.mpgc.rpg125668.model.dispenser;

import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IStudent;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IPurchasable;
import it.unicam.cs.mpgc.rpg125668.model.dispenser.interfaces.IShop;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;
import java.util.Map;

public abstract class Dispenser implements IShop {
    protected final String name;
    protected final Map<IItem, Integer> items;

    public Dispenser(String name, Map<IItem, Integer> items) {
        this.name = name;
        this.items = items;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Map<IItem, Integer> getItems(){
        return this.items;
    }

    @Override
    public void buyItem(IStudent<IStudentSkill> student, IPurchasable item) {
        if(student == null || item == null)throw new IllegalArgumentException("Illegal arguments: student or item is null");
        if(student.getInventory().seeCoins() < item.getPrice()) return;
        addItem(student,item);
    }

    @Override
    public void restockItems(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Illegal arguments: amount < 0");
        this.items.replaceAll((item, quantity) -> quantity + amount);
    }

    @Override
    public void addItemToShop(IItem item, int quantity){
        if(item == null || quantity < 0) throw new IllegalArgumentException("Illegal arguments: item is null or quantity < 0");
        if ( !this.items.containsKey(item))
            this.items.put(item, quantity);
        else
            this.items.put(item, this.items.get(item) + quantity);
    }

    private void addItem(IStudent<IStudentSkill> student, IPurchasable item) {
        student.getInventory().addItem(item);
        student.getInventory().removeCoins(item.getPrice());
        items.put(item, items.getOrDefault(item, 0) - 1);
        if (items.get(item) == 0)
            items.remove(item);
    }
}
