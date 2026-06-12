package it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces;
import java.util.List;

/**
 * Interface for a room
 */
public interface IRoom {
    /**
     * Get the name of the room
     * @return String name of the room
     */
    String getName();

    /**
     * Get the description of the room
     * @return String description of the room
     */
    String getDescription();

    /**
     * Get the exits of the room
     * @return List<IRoom> exits of the room
     */
    List<IRoom> getExits();

}
