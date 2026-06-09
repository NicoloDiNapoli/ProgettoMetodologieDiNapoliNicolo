package it.unicam.cs.mpgc.rpg125668.persistence.store.character;

import it.unicam.cs.mpgc.rpg125668.model.enumeration.EnemyDifficult;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.character.EnemyEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.store.BaseStore;

import java.util.List;

public class EnemyStore extends BaseStore<EnemyEntity> {

    public EnemyStore() {
        super(EnemyEntity.class);
    }

    public List<EnemyEntity> selectAll() {
        return entityManager.createQuery("select e from EnemyEntity e",
                EnemyEntity.class)
                .getResultList();
    }
}