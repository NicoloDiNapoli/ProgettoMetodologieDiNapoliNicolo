package it.unicam.cs.mpgc.rpg125668.logic.data;

import it.unicam.cs.mpgc.rpg125668.logic.dto.GameSaveData;
import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IEnemy;
import it.unicam.cs.mpgc.rpg125668.model.consumable.interfaces.IItem;
import it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces.IRoom;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IBossSkill;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.game.GameStateEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.character.EnemyEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.item.ItemEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.room.RoomEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.skill.BossSkillEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.skill.StudentSkillEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.mapper.character.EnemyMapper;
import it.unicam.cs.mpgc.rpg125668.persistence.mapper.character.StudentMapper;
import it.unicam.cs.mpgc.rpg125668.persistence.mapper.item.ItemMapper;
import it.unicam.cs.mpgc.rpg125668.persistence.mapper.room.RoomMapper;
import it.unicam.cs.mpgc.rpg125668.persistence.mapper.skills.StudentSkillMapper;
import it.unicam.cs.mpgc.rpg125668.persistence.store.character.EnemyStore;
import it.unicam.cs.mpgc.rpg125668.persistence.store.item.ItemStore;
import it.unicam.cs.mpgc.rpg125668.persistence.store.room.RoomStore;
import it.unicam.cs.mpgc.rpg125668.persistence.store.skill.SkillStore;
import it.unicam.cs.mpgc.rpg125668.persistence.store.state.GameStateStore;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that contains all the data needed to start the game, that is loaded from the database
 */
public class LoadedData {
    private final List<StudentSkillEntity> studentSkills;
    private final List<BossSkillEntity> bossSkills;
    private final List<ItemEntity> items;
    private final List<RoomEntity> rooms;
    private final List<EnemyEntity> enemies;
    private final List<GameStateEntity> saves;

    public LoadedData(){
        this.studentSkills = new SkillStore<>(StudentSkillEntity.class).findAll();
        this.bossSkills = new SkillStore<>(BossSkillEntity.class).findAll();
        this.items = new ItemStore().findAll();
        this.rooms = new RoomStore().findAll();
        this.enemies = new EnemyStore().findAll();
        this.saves = new GameStateStore().findAll();
    }

    /**
     * Returns the list of skills from the database
     * @return List<StudentSkillEntity> the list of skills
     */
    public List<IStudentSkill> getStudentSkills() {
        return studentSkills.stream().map(StudentSkillMapper::toModel).collect(Collectors.toList());
    }


    public List<IItem> getItems() {
        return items.stream().map(ItemMapper::toModel).collect(Collectors.toList());
    }
    public List<IRoom> getRooms() {
        return rooms.stream().map(RoomMapper::toModel).collect(Collectors.toList());
    }
    public List<IEnemy<IBossSkill>> getEnemies() {
        return enemies.stream().map(EnemyMapper::toModel).collect(Collectors.toList());
    }

    /**
     * Returns the list of rooms from the database
     * @return List<RoomEntity> the list of rooms
     */
    public List<RoomEntity> getRoomEntities() {
        return rooms;
    }
    //load a list of saves from the db to load the game
    public List<GameSaveData> getSaves() {
        return saves.stream()
                .map(s -> new GameSaveData(
                        s.getSaveGameName(),
                        s.getSaveGameTime(),
                        StudentMapper.toModel(s.getStudent()),
                        RoomMapper.toModel(s.getCurrentRoom())
                ))
                .toList();
    }
}
