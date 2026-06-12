package it.unicam.cs.mpgc.rpg125668.logic.start;

import it.unicam.cs.mpgc.rpg125668.logic.data.LoadedData;
import it.unicam.cs.mpgc.rpg125668.logic.manager.GameManager;
import it.unicam.cs.mpgc.rpg125668.logic.manager.interfaces.IGame;
import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IEnemy;
import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IStudent;
import it.unicam.cs.mpgc.rpg125668.model.rooms.GameMap;
import it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces.IRoom;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IBossSkill;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Game implements IGame {
    protected IStudent<IStudentSkill> player;
    protected final LoadedData loadedData;
    protected List<IEnemy<IBossSkill>> enemies;
    protected GameMap gameMap;
    protected List<IStudentSkill> studentSkillsToUnlock;
    protected GameManager gameManager;

    public Game() {
        this.loadedData = new LoadedData();
    }

    /**
     * Creates a GameMap from the rooms in the database and sets the current room.
     * @param currentRoomName String name of the current room
     * @return GameMap the created GameMap
     */
    public GameMap createGameMap(String currentRoomName) {
        Map<String, IRoom> roomMap = this.loadedData.getRooms().stream()
                .collect(Collectors.toMap(IRoom::getName, r -> r));
        return new GameMap(roomMap, roomMap.get(currentRoomName));
    }

    /**
     * Starts the game and creates the GameManager.
     */
    public void start(){
        this.gameManager = new GameManager(this.player, this.enemies, this.gameMap, this.studentSkillsToUnlock);
    }

    /**
     * Get the GameManager of the game.
     * @return GameManager the GameManager of the game.
     */
    public GameManager getGameManager(){return this.gameManager;}
}
