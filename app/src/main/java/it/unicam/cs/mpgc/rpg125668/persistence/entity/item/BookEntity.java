package it.unicam.cs.mpgc.rpg125668.persistence.entity.item;

import it.unicam.cs.mpgc.rpg125668.model.enumeration.BookRarity;
import jakarta.persistence.*;

@Entity
@Table(name = "BOOK")
public class BookEntity extends ItemEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "rarity", nullable = false)
    private BookRarity rarity;

    protected BookEntity() {}

    public BookEntity(String name, BookRarity rarity) {
        super(name, "book", rarity.getPrice());
        this.rarity = rarity;
    }

    public BookRarity getRarity() { return rarity; }
}