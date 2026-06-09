package it.unicam.cs.mpgc.rpg125668.persistence.store.character;

import it.unicam.cs.mpgc.rpg125668.persistence.entity.character.EnemyEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.store.BaseStore;
import it.unicam.cs.mpgc.rpg125668.persistence.store.character.interfaces.IEnemyStore;

import java.util.List;

public class EnemyStore extends BaseStore<EnemyEntity> implements IEnemyStore {

    public EnemyStore() {
        super(EnemyEntity.class);
    }

    public List<EnemyEntity> selectAll() {
        return entityManager.createQuery("select e from EnemyEntity e",
                EnemyEntity.class)
                .getResultList();
    }
}