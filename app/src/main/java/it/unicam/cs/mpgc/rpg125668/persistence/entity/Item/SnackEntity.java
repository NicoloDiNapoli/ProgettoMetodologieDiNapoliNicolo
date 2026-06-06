package it.unicam.cs.mpgc.rpg125668.persistence.entity.Item;

import jakarta.persistence.*;

@Entity
@Table(name = "SNACK")
public class SnackEntity extends ItemEntity {

    @Column(name = "increase_life", nullable = false)
    private int increaseLife;

    protected SnackEntity() {}

    public SnackEntity(String name, int increaseLife, int price) {
        super(name, "snack", price);
        this.increaseLife = increaseLife;
    }

    public int getIncreaseLife() { return increaseLife; }
}