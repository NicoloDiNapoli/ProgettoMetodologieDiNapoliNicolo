package it.unicam.cs.mpgc.rpg125668.persistence.entity.inventory;

import it.unicam.cs.mpgc.rpg125668.persistence.entity.item.ItemEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "INVENTORY_ITEM")
public class InventoryItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inventory_id", nullable = false)
    private InventoryEntity inventory;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private ItemEntity item;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    protected InventoryItemEntity() {}

    public InventoryItemEntity(InventoryEntity inventory, ItemEntity item, int quantity) {
        this.inventory = inventory;
        this.item = item;
        this.quantity = quantity;
    }

    public Long getId() { return id; }
    public InventoryEntity getInventory() { return inventory; }
    public ItemEntity getItem() { return item; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public void setItem(ItemEntity dbItem) {this.item = dbItem;}

    public void setInventory(InventoryEntity inventory) {this.inventory = inventory;}
}