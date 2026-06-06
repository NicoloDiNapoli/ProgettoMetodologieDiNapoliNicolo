package it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces;
import java.util.Map;

public interface IGameMap {
    /**
     * Get the list of rooms of the game
     * @return Map<String, IRoom> of rooms
     */
    Map<String, IRoom> getRooms();
}

