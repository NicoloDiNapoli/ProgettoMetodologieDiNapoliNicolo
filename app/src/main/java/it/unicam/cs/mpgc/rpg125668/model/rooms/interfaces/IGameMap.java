package it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces;
import java.util.List;
import java.util.Map;

/**
 * Interface for the game map of the game
 */
public interface IGameMap {
    /**
     * Get the list of rooms of the game
     * @return Map<String, IRoom> of rooms
     */
    Map<String, IRoom> getRooms();

    /**
     * Set the current room of the game
     * @param roomName String name of the room
     */
    void setCurrentRoom(String roomName);

    /**
     * Get the current room of the game
     * @return IRoom current room
     */
    public IRoom getCurrentRoom();

    /**
     * Get avaiable exits of the current room
     * @return List<IRoom> exits of the current room
     */
    List<IRoom> getAvailableExits();
}

