package it.unicam.cs.mpgc.rpg125668.utils;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;

import java.util.List;
import java.util.Map;

public class ItemSaveNode {
    private final IItem item;
    private int quantity;

    public ItemSaveNode(IItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public static List<ItemSaveNode> mapToList(Map<IItem, Integer> inventory) {
        return inventory.entrySet().stream().
                map(entry -> new ItemSaveNode(entry.getKey(), entry.getValue()))
                .toList();
    }


}
