package it.unicam.cs.mpgc.rpg125668.persistence.mapper.game;


import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IStudent;
import it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces.IRoom;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.game.GameStateEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.room.RoomEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.mapper.character.StudentMapper;
import it.unicam.cs.mpgc.rpg125668.persistence.mapper.room.RoomMapper;

public class GameStateMapper {

    public static GameStateEntity toEntity(IStudent<IStudentSkill> player, RoomEntity currentRoom, String saveGameName) {
        if (player == null || currentRoom == null) throw new IllegalArgumentException("Arguments cannot be null");
        return new GameStateEntity(
                StudentMapper.toEntity(player),
                currentRoom,
                saveGameName
        );
    }

    public static IStudent<IStudentSkill> toPlayerModel(GameStateEntity entity) {
        if (entity == null) throw new IllegalArgumentException("Entity cannot be null");
        return StudentMapper.toModel(entity.getStudent());
    }

    public static IRoom toRoomModel(GameStateEntity entity) {
        if (entity == null) throw new IllegalArgumentException("Entity cannot be null");
        return RoomMapper.toModel(entity.getCurrentRoom());
    }
}