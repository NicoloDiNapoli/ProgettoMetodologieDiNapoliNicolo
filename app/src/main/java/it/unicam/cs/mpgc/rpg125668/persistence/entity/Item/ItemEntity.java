package it.unicam.cs.mpgc.rpg125668.persistence.entity.Item;

import jakarta.persistence.*;

@Entity
@Table(name = "ITEM")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "price", nullable = false)
    private int price;

    protected ItemEntity() {}

    public ItemEntity(String name, String type, int price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public int getPrice() { return price; }
}