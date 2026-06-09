package it.unicam.cs.mpgc.rpg125668.persistence.mapper;

import it.unicam.cs.mpgc.rpg125668.model.consumable.Book;
import it.unicam.cs.mpgc.rpg125668.model.consumable.Drink;
import it.unicam.cs.mpgc.rpg125668.model.consumable.Snack;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.item.BookEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.item.DrinkEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.item.ItemEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.item.SnackEntity;

public class ItemMapper {

    public static IItem toModel(ItemEntity entity) {
        return switch (entity) {
            case null -> throw new IllegalArgumentException("Entity cannot be null");
            case BookEntity b -> new Book(b.getName(), b.getRarity());
            case SnackEntity s -> new Snack(s.getName(), s.getIncreaseLife(), s.getPrice());
            case DrinkEntity d -> new Drink(d.getName(), d.getIncreaseConcentration(), d.getPrice());
            default -> throw new IllegalArgumentException("Unknown item type: " + entity.getClass().getSimpleName());
        };
    }

    public static ItemEntity toEntity(IItem model) {
        return switch (model) {
            case null -> throw new IllegalArgumentException("Model cannot be null");
            case Book book -> new BookEntity(book.getName(), book.getRarity());
            case Snack snack -> new SnackEntity(snack.getName(), snack.getIncrease(), snack.getPrice());
            case Drink drink -> new DrinkEntity(drink.getName(), drink.getIncrease(), drink.getPrice());
            default -> throw new IllegalArgumentException("Unknown item type: " + model.getClass().getSimpleName());
        };
    }
}