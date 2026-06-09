package it.unicam.cs.mpgc.rpg125668.persistence.store.inventory;

import it.unicam.cs.mpgc.rpg125668.persistence.entity.inventory.InventoryEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.store.BaseStore;

public class InventoryStore extends BaseStore<InventoryEntity> {

    public InventoryStore() {
        super(InventoryEntity.class);
    }
}