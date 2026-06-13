package it.unicam.cs.mpgc.rpg125668.logic.dto;

import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IStudent;
import it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces.IRoom;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that represents the data of a game save
 */
public class GameSaveData {

    private final String saveName;
    private final LocalDateTime saveTime;
    private final IStudent<IStudentSkill> player;
    private final IRoom currentRoom;

    public GameSaveData(String saveName, LocalDateTime saveTime, IStudent<IStudentSkill> player, IRoom currentRoom) {
        this.saveName = saveName;
        this.saveTime = saveTime;
        this.player = player;
        this.currentRoom = currentRoom;
    }

    /**
     * Returns a string representation of the save time in the format "dd/MM/yyyy HH:mm".
     * @return String the formatted save time.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return saveTime != null ? saveTime.format(formatter) : "Data sconosciuta";
    }

    public String getSaveName() { return saveName; }
    public LocalDateTime getSaveTime() { return saveTime; }
    public IStudent<IStudentSkill> getPlayer() { return player; }
    public IRoom getCurrentRoom() { return currentRoom; }
}
