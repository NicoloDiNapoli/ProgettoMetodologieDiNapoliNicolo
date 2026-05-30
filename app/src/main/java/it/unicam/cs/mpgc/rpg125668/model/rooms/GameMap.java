package it.unicam.cs.mpgc.rpg125668.model.rooms;

import it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces.IGameMap;
import it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces.IRoom;

import java.util.Map;

public class GameMap implements IGameMap {
    private final Map<String, IRoom> rooms;
    private final IRoom startRoom;

    public GameMap(Map<String, IRoom> rooms, IRoom startRoom) {
        if(rooms == null || startRoom == null) throw new IllegalArgumentException("Illegal arguments: rooms or startRoom is null");
        this.rooms = rooms;
        this.startRoom = startRoom;
    }

    public IRoom getStartRoom() {return startRoom;}
    public Map<String, IRoom> getRooms() {return rooms;}

}
