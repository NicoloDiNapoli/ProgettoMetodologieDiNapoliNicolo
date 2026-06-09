package it.unicam.cs.mpgc.rpg125668.persistence.store.item.interfaces;

import it.unicam.cs.mpgc.rpg125668.persistence.entity.item.BookEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.item.DrinkEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.item.ItemEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.item.SnackEntity;

import java.util.List;

/**
 * Interface for Item Query
 */
public interface IItemStore {
    /**
     * Select books items and return them as a list
     * @return List<BookEntity> of books
     */
    List<BookEntity> findAllBooks();

    /**
     * Select all snacks and return them as a list
     * @return List<SnackEntity> of snacks and List<DrinkEntity> of drinks
     */
    List<SnackEntity> findAllSnacks();

    /**
     * Select all drinks and return them as a list
     * @return List<DrinkEntity> of drinks
     */
    List<DrinkEntity> findAllDrinks();

    /**
     * Find an item by its name
     * @param name String name of the item
     * @return ItemEntity item
     */
    ItemEntity findByName(String name);
}
