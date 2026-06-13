package it.unicam.cs.mpgc.rpg125668.logic.manager;

import it.unicam.cs.mpgc.rpg125668.logic.data.LoadedData;
import it.unicam.cs.mpgc.rpg125668.logic.manager.interfaces.IGameSaveManager;
import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IStudent;
import it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces.IGameMap;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.game.GameStateEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.room.RoomEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.mapper.game.GameStateMapper;
import it.unicam.cs.mpgc.rpg125668.persistence.mapper.character.StudentMapper;
import it.unicam.cs.mpgc.rpg125668.persistence.store.state.GameStateStore;

import java.time.LocalDateTime;

/**
 * Class that manages the saving of the game
 */
public class GameSaveManager implements IGameSaveManager {

    private final GameStateStore gameStateStore = new GameStateStore();
    private final LoadedData loadedData = new LoadedData();

    @Override
    public void saveGame(IStudent<IStudentSkill> player, IGameMap gameMap, String saveGameName) {
        RoomEntity currentRoomEntity = loadedData.getRoomEntities().stream()
                .filter(r -> r.getName().equals(gameMap.getCurrentRoom().getName()))
                .findFirst()
                .orElseThrow();

        GameStateEntity existing = gameStateStore.findByName(saveGameName);

        if (existing != null) {
            StudentMapper.updateEntity(player, existing.getStudent());
            existing.setCurrentRoom(currentRoomEntity);
            existing.setSaveGameTime(LocalDateTime.now());
            gameStateStore.update(existing);
        } else {
            GameStateEntity entity = GameStateMapper.toEntity(player, currentRoomEntity, saveGameName);
            gameStateStore.save(entity);
        }
    }
}