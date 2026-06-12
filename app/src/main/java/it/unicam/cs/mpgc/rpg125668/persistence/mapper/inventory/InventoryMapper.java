package it.unicam.cs.mpgc.rpg125668.persistence.mapper.inventory;

import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;
import it.unicam.cs.mpgc.rpg125668.model.inventory.Inventory;
import it.unicam.cs.mpgc.rpg125668.model.inventory.interfaces.IInventory;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.inventory.InventoryEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.inventory.InventoryItemEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.mapper.item.ItemMapper;

import java.util.HashMap;
import java.util.Map;

public class InventoryMapper {

    public static IInventory toModel(InventoryEntity entity) {
        if (entity == null) throw new IllegalArgumentException("Entity cannot be null");
        Map<IItem, Integer> items = new HashMap<>();
        for (InventoryItemEntity itemEntity : entity.getItems()) {
            IItem item = ItemMapper.toModel(itemEntity.getItem());
            items.put(item, itemEntity.getQuantity());
        }
        return new Inventory(entity.getCoins(), items);
    }

    public static InventoryEntity toEntity(IInventory model) {
        if (model == null) throw new IllegalArgumentException("Model cannot be null");
        InventoryEntity entity = new InventoryEntity(model.seeCoins());
        model.getItems().forEach((item, quantity) -> {
            InventoryItemEntity itemEntity = new InventoryItemEntity(
                    entity,
                    ItemMapper.toEntity(item),
                    quantity
            );
            entity.addItem(itemEntity);
        });
        return entity;
    }
}