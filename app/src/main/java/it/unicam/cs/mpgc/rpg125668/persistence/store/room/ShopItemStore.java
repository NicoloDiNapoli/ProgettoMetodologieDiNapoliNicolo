package it.unicam.cs.mpgc.rpg125668.persistence.store.room;

import it.unicam.cs.mpgc.rpg125668.persistence.entity.room.ShopItemEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.room.ShopRoomEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.store.BaseStore;

import java.util.List;

public class ShopItemStore extends BaseStore<ShopItemEntity> {

    public ShopItemStore() {
        super(ShopItemEntity.class);
    }

    public List<ShopItemEntity> findByShopRoom(ShopRoomEntity shopRoom) {
        return entityManager.createQuery("select s from ShopItemEntity s where s.shopRoom = :shopRoom",
                ShopItemEntity.class)
                .setParameter("shopRoom", shopRoom)
                .getResultList();
    }
}