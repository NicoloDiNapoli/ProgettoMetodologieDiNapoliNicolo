package it.unicam.cs.mpgc.rpg125668.persistence.store.room;

import it.unicam.cs.mpgc.rpg125668.persistence.entity.room.RoomEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.store.BaseStore;

import java.util.List;

public class RoomStore extends BaseStore<RoomEntity> {

    public RoomStore() {
        super(RoomEntity.class);
    }

    public List<RoomEntity> selectAll() {
        return entityManager.createQuery("select r from RoomEntity r",
                RoomEntity.class)
                .getResultList();
    }
}