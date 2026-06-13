package it.unicam.cs.mpgc.rpg125668.persistence.mapper.character;

import it.unicam.cs.mpgc.rpg125668.model.characters.Level;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.character.LevelEntity;

public class LevelMapper {

    /**
     * Convert a LevelEntity to a Level
     * @param entity LevelEntity
     * @return Level
     */
    public static Level toModel(LevelEntity entity) {
        if (entity == null) throw new IllegalArgumentException("Entity cannot be null");
        return new Level(
                entity.getLevel(),
                entity.getExperience(),
                entity.getNextLevelExperience()
        );
    }

    /**
     * Convert a Level to a LevelEntity
     * @param model Level
     * @return LevelEntity
     */
    public static LevelEntity toEntity(Level model) {
        if (model == null) throw new IllegalArgumentException("Model cannot be null");
        return new LevelEntity(
                model.getLevel(),
                model.getExperience(),
                model.getNextLevelExperience()
        );
    }
}