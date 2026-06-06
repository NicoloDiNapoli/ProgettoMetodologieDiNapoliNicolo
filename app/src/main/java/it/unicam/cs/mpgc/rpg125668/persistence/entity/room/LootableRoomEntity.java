package it.unicam.cs.mpgc.rpg125668.persistence.entity.room;

import it.unicam.cs.mpgc.rpg125668.persistence.entity.inventory.InventoryEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "LOOTABLE_ROOM")
public class LootableRoomEntity extends RoomEntity {

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "inventory_id")
    private InventoryEntity roomInventory;

    @Column(name = "looted", nullable = false)
    private boolean looted;

    protected LootableRoomEntity() {}

    public LootableRoomEntity(String name, String description,
                              java.util.List<RoomEntity> exits,
                              InventoryEntity roomInventory) {
        super(name, description, exits);
        this.roomInventory = roomInventory;
        this.looted = false;
    }

    public InventoryEntity getRoomInventory() { return roomInventory; }
    public boolean isLooted() { return looted; }
    public void setLooted(boolean looted) { this.looted = looted; }
    public void setRoomInventory(InventoryEntity roomInventory) { this.roomInventory = roomInventory; }
}