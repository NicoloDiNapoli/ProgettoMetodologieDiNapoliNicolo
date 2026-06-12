package it.unicam.cs.mpgc.rpg125668.persistence.store.state;

import it.unicam.cs.mpgc.rpg125668.persistence.entity.game.GameStateEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.store.BaseStore;

import java.util.List;

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

    public GameStateEntity findByName(String saveGameName) {
        List<GameStateEntity> results = entityManager.createQuery(
                        "select e from GameStateEntity e where e.saveGameName = :saveGameName", GameStateEntity.class)
                .setParameter("saveGameName", saveGameName)
                .getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}