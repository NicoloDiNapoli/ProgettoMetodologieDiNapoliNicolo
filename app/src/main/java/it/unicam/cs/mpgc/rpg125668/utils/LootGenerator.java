package it.unicam.cs.mpgc.rpg125668.utils;

import it.unicam.cs.mpgc.rpg125668.logic.data.LoadedData;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;
import it.unicam.cs.mpgc.rpg125668.model.rooms.LootableRoom;
import it.unicam.cs.mpgc.rpg125668.model.rooms.ShopRoom;
import it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces.IGameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class that generates loot in the game
 * and restocks the dispensers
 */
public class LootGenerator {

    private static final LoadedData loadedData = new LoadedData();

    /**
     * Generate loot in LootableRooms of the map
     * @param map IGameMap map of the game
     */
    public static void generateLoot(IGameMap map) {
        Random random = new Random();
        List<IItem> allItems = loadedData.getItems();
        int itemCount = random.nextInt(3) + 1;
        int coins = random.nextInt(51);

        List<IItem> loot = new ArrayList<>();
        for (int i = 0; i < itemCount; i++)
            loot.add(allItems.get(random.nextInt(allItems.size())));

        map.getRooms().values().stream()
                .filter(r -> r instanceof LootableRoom)
                .map(r -> (LootableRoom) r)
                .forEach(r -> r.addGeneratedLoot(loot, coins));

    }

    /**
     * Restock the dispensers of the ShopRooms of the map
     * @param map IGameMap map of the game
     */
    public static void restockDispensers(IGameMap map) {
        map.getRooms().values().stream()
                .filter(r -> r instanceof ShopRoom)
                .map(r -> (ShopRoom) r)
                .forEach(r -> r.getDispenser().restockItems(10));
    }
}
