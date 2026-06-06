package it.unicam.cs.mpgc.rpg125668.persistence.entity.inventory;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "INVENTORY")
public class InventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "coins", nullable = false)
    private int coins;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventoryItemEntity> items = new ArrayList<>();

    protected InventoryEntity() {}

    public InventoryEntity(int coins) {
        this.coins = coins;
    }

    public Long getId() { return id; }
    public int getCoins() { return coins; }
    public List<InventoryItemEntity> getItems() { return items; }
    public void setCoins(int coins) { this.coins = coins; }
    public void addItem(InventoryItemEntity item) { this.items.add(item); }
}