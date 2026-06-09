package it.unicam.cs.mpgc.rpg125668.persistence.store.item;

import it.unicam.cs.mpgc.rpg125668.persistence.entity.item.BookEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.item.DrinkEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.item.ItemEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.item.SnackEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.store.BaseStore;

import java.util.List;

public class ItemStore extends BaseStore<ItemEntity> {

    public ItemStore() {
        super(ItemEntity.class);
    }

    public List<BookEntity> findAllBooks() {
        return entityManager.createQuery(
                        "SELECT b FROM BookEntity b",
                        BookEntity.class)
                .getResultList();
    }

    public List<SnackEntity> findAllSnacks() {
        return entityManager.createQuery(
                        "SELECT s FROM SnackEntity s",
                        SnackEntity.class)
                .getResultList();
    }

    public List<DrinkEntity> findAllDrinks() {
        return entityManager.createQuery(
                        "SELECT d FROM DrinkEntity d",
                        DrinkEntity.class)
                .getResultList();
    }

    public ItemEntity findByName(String name) {
        return entityManager.createQuery(
                        "SELECT i FROM ItemEntity i WHERE i.name = :name",
                        ItemEntity.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}