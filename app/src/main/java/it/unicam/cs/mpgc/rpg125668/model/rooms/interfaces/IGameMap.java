package it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces;
import java.util.Map;

public interface IGameMap {
    /**
     * Get the start room of the game
     * @return main room of the game
     */
    IRoom getStartRoom();

    /**
     * Get the list of rooms of the game
     * @return Map<String, IRoom> of rooms
     */
    Map<String, IRoom> getRooms();
}

