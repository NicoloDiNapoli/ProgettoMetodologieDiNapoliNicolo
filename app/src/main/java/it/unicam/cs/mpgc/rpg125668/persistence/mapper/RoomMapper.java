package it.unicam.cs.mpgc.rpg125668.persistence.mapper;

import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;
import it.unicam.cs.mpgc.rpg125668.model.dispenser.BookDispenser;
import it.unicam.cs.mpgc.rpg125668.model.dispenser.SnackDrinkDispenser;
import it.unicam.cs.mpgc.rpg125668.model.inventory.Inventory;
import it.unicam.cs.mpgc.rpg125668.model.inventory.interfaces.IInventory;
import it.unicam.cs.mpgc.rpg125668.model.rooms.LootableRoom;
import it.unicam.cs.mpgc.rpg125668.model.rooms.Room;
import it.unicam.cs.mpgc.rpg125668.model.rooms.ShopRoom;
import it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces.IRoom;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.room.LootableRoomEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.room.RoomEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.room.ShopRoomEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomMapper {

    /**
     * public method to map a RoomEntity to a IRoom
     * @param entity room entity to map
     * @return IRoom mapped room
     */
    public static IRoom toModel(RoomEntity entity) {
        if (entity == null) throw new IllegalArgumentException("Entity cannot be null");

        Map<String, IRoom> visitedRooms = new HashMap<>();
        return toModelInternal(entity, visitedRooms);
    }

    /**
     * private method to map a RoomEntity to a IRoom to manage recursive calls
     * of loop calls to the room connections
     * @param entity room entity to map
     * @param visitedRooms map of visited rooms to avoid loops
     * @return IRoom mapped room
     */
    private static IRoom toModelInternal(RoomEntity entity, Map<String, IRoom> visitedRooms) {
        if (entity == null) throw new IllegalArgumentException("Entity cannot be null");

        if (visitedRooms.containsKey(entity.getName())) {
            return visitedRooms.get(entity.getName());
        }

        List<IRoom> exits = new ArrayList<>();
        IRoom currentRoom = switch (entity) {
            case LootableRoomEntity lootableRoomEntity -> isLootableRoom(lootableRoomEntity, exits);
            case ShopRoomEntity shopRoomEntity -> shopRoom(shopRoomEntity, entity, exits);
            default -> new Room(entity.getName(), exits, entity.getDescription());
        };

        visitedRooms.put(entity.getName(), currentRoom);

        if (entity.getExits() != null) {
            entity.getExits().forEach(exit -> exits.add(toModelInternal(exit, visitedRooms)));
        }
        return currentRoom;
    }

    /**
     * private method that returns a ShopRoom if the entity is a ShopRoomEntity
     * @param shopRoomEntity ShopRoomEntity to map
     * @param entity RoomEntity to map
     * @param exits List<IRoom> exits of the room
     * @return ShopRoom mapped room
     */
    private static IRoom shopRoom(ShopRoomEntity shopRoomEntity, RoomEntity entity, List<IRoom> exits) {
        Map<IItem,Integer> items = new HashMap<>();
        shopRoomEntity.getShopItems().forEach(si ->
                items.put(ItemMapper.toModel(si.getItem()), si.getQuantity())
        );
        if (shopRoomEntity.getDispenserName().contains("Book"))
            return new ShopRoom(entity.getName(), new ArrayList<>(exits), entity.getDescription(), new BookDispenser(items));
        else
            return new ShopRoom(entity.getName(), new ArrayList<>(exits), entity.getDescription(), new SnackDrinkDispenser(items));
    }

    /**
     * private method that returns a LootableRoom if the entity is a LootableRoomEntity
     * @param entity RoomEntity to map
     * @param exits List<IRoom> exits of the room
     * @return LootableRoom mapped room
     */
    private static IRoom isLootableRoom(RoomEntity entity, List<IRoom> exits) {
        IInventory inventory;
        LootableRoomEntity lootableRoomEntity = (LootableRoomEntity) entity;
        if(lootableRoomEntity.getRoomInventory() != null)
            inventory = InventoryMapper.toModel(lootableRoomEntity.getRoomInventory());
        else
            inventory = new Inventory(0, new HashMap<>());
        return new LootableRoom(entity.getName(), new ArrayList<>(exits), entity.getDescription(), inventory);
    }

}