package it.unicam.cs.mpgc.rpg125668.persistence.store.character.interfaces;

import it.unicam.cs.mpgc.rpg125668.persistence.entity.character.EnemyEntity;

import java.util.List;

/**
 * Interface for Enemy Query
 */
public interface IEnemyStore {
    /**
     * Select all enemies and return them as a list
     * @return List<EnemyEntity> enemies
     */
    List<EnemyEntity> selectAll();
}
