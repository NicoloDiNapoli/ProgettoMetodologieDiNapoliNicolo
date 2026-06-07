package it.unicam.cs.mpgc.rpg125668.persistence.entity.item;

import jakarta.persistence.*;

@Entity
@Table(name = "DRINK")
public class DrinkEntity extends ItemEntity {

    @Column(name = "increase_concentration", nullable = false)
    private int increaseConcentration;

    protected DrinkEntity() {}

    public DrinkEntity(String name, int increaseConcentration, int price) {
        super(name, "drink", price);
        this.increaseConcentration = increaseConcentration;
    }

    public int getIncreaseConcentration() { return increaseConcentration; }
}