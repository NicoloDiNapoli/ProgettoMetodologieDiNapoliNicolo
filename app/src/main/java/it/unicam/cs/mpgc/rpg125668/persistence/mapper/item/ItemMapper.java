package it.unicam.cs.mpgc.rpg125668.persistence.mapper.item;

import it.unicam.cs.mpgc.rpg125668.model.consumable.Book;
import it.unicam.cs.mpgc.rpg125668.model.consumable.Drink;
import it.unicam.cs.mpgc.rpg125668.model.consumable.Snack;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.item.BookEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.item.DrinkEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.item.ItemEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.item.SnackEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.store.item.ItemStore;

public class ItemMapper {

    /**
     * Convert an ItemEntity to an Item
     * @param entity ItemEntity
     * @return Item
     */
    public static IItem toModel(ItemEntity entity) {
        return switch (entity) {
            case null -> throw new IllegalArgumentException("Entity cannot be null");
            case BookEntity b -> new Book(b.getName(), b.getRarity());
            case SnackEntity s -> new Snack(s.getName(), s.getIncreaseLife(), s.getPrice());
            case DrinkEntity d -> new Drink(d.getName(), d.getIncreaseConcentration(), d.getPrice());
            default -> throw new IllegalArgumentException("Unknown item type: " + entity.getClass().getSimpleName());
        };
    }

    /**
     * Convert an Item to an ItemEntity
     * @param model Item
     * @return ItemEntity
     */
    public static ItemEntity toEntity(IItem model) {
        if (model == null) throw new IllegalArgumentException("Model cannot be null");
        return new ItemStore().findByName(model.getName());
    }
}