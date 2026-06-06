package it.unicam.cs.mpgc.rpg125668.model.rooms;

import it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces.IGameMap;
import it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces.IRoom;

import java.util.Map;

public class GameMap implements IGameMap {
    private final Map<String, IRoom> rooms;

    public GameMap(Map<String, IRoom> rooms, IRoom startRoom) {
        if(rooms == null || startRoom == null) throw new IllegalArgumentException("Illegal arguments: rooms or startRoom is null");
        this.rooms = rooms;
    }

    public Map<String, IRoom> getRooms() {return rooms;}

}
