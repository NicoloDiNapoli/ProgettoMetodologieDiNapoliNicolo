package it.unicam.cs.mpgc.rpg125668.model.rooms;

import it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces.IGameMap;
import it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces.IRoom;

import java.util.List;
import java.util.Map;

public class GameMap implements IGameMap {
    private final Map<String, IRoom> rooms;
    private IRoom currentRoom;

    public GameMap(Map<String, IRoom> rooms, IRoom startRoom) {
        if(rooms == null || startRoom == null) throw new IllegalArgumentException("Illegal arguments: rooms or startRoom is null");
        this.rooms = rooms;
        this.currentRoom = startRoom;
    }

    @Override
    public Map<String, IRoom> getRooms() {return rooms;}


    @Override
    public void setCurrentRoom(String room) {
        if(room == null) throw new IllegalArgumentException("Illegal arguments: room is null");
        this.currentRoom = this.rooms.get(room);
    }

    @Override
    public IRoom getCurrentRoom() {return currentRoom;}

    @Override
    public List<IRoom> getAvailableExits() {
        return currentRoom.getExits();
    }


}
