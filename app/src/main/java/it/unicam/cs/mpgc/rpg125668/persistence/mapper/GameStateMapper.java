package it.unicam.cs.mpgc.rpg125668.persistence.mapper;

//import it.unicam.cs.mpgc.rpg125668.logic.GameState;
import it.unicam.cs.mpgc.rpg125668.model.characters.Student;
import it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces.IRoom;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.GameStateEntity;

public class GameStateMapper {

    /*public static GameState toModel(GameStateEntity entity) {
        if (entity == null) throw new IllegalArgumentException("Entity cannot be null");
        Student student = StudentMapper.toModel(entity.getStudent());
        IRoom currentRoom = RoomMapper.toModel(entity.getCurrentRoom());
        return new GameState(student, currentRoom);
    }

    public static GameStateEntity toEntity(GameState model, String saveGameName) {
        if (model == null) throw new IllegalArgumentException("Model cannot be null");
        return new GameStateEntity(
                StudentMapper.toEntity(model.getStudent()),
                model.getCurrentRoom(),
                saveGameName
        );
    }*/
}