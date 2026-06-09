package it.unicam.cs.mpgc.rpg125668.persistence.store.character;

import it.unicam.cs.mpgc.rpg125668.persistence.entity.character.LevelEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.store.BaseStore;

public class LevelStore extends BaseStore<LevelEntity> {

    public LevelStore() {
        super(LevelEntity.class);
    }
}