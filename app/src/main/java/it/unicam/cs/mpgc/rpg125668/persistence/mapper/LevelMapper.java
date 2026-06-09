package it.unicam.cs.mpgc.rpg125668.persistence.mapper;

import it.unicam.cs.mpgc.rpg125668.model.characters.Level;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.character.LevelEntity;

public class LevelMapper {

    public static Level toModel(LevelEntity entity) {
        if (entity == null) throw new IllegalArgumentException("Entity cannot be null");
        return new Level(
                entity.getLevel(),
                entity.getExperience(),
                entity.getNextLevelExperience()
        );
    }

    public static LevelEntity toEntity(Level model) {
        if (model == null) throw new IllegalArgumentException("Model cannot be null");
        return new LevelEntity(
                model.getLevel(),
                model.getExperience(),
                model.getNextLevelExperience()
        );
    }
}