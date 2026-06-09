package it.unicam.cs.mpgc.rpg125668.persistence.store.state;

import it.unicam.cs.mpgc.rpg125668.persistence.entity.GameStateEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.store.BaseStore;

public class GameStateStore extends BaseStore<GameStateEntity> {

    public GameStateStore() {
        super(GameStateEntity.class);
    }

    public Long countSaves() {
        return entityManager.createQuery("select count(e) from GameStateEntity e", Long.class).getSingleResult();
    }

    public GameStateEntity getGameStateEntity(int id) {
        return entityManager.find(GameStateEntity.class, id);
    }
}