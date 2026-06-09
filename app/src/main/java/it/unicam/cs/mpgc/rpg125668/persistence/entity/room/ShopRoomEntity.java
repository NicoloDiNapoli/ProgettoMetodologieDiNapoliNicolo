package it.unicam.cs.mpgc.rpg125668.persistence.entity.room;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SHOP_ROOM")
public class ShopRoomEntity extends RoomEntity {

    @Column(name = "dispenser_name", nullable = false)
    private String dispenserName;

    @OneToMany(mappedBy = "shopRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShopItemEntity> shopItems = new ArrayList<>();

    protected ShopRoomEntity() {}

    public ShopRoomEntity(String name, String description,
                          List<RoomEntity> exits,
                          String dispenserName) {
        super(name, description, exits);
        this.dispenserName = dispenserName;
    }

    public String getDispenserName() { return dispenserName; }
    public List<ShopItemEntity> getShopItems() { return shopItems; }
    public void addShopItem(ShopItemEntity item) { this.shopItems.add(item); }
    public void setShopItems(List<ShopItemEntity> shopItems) { this.shopItems = shopItems; }
    public void setDispenserName(String dispenserName) { this.dispenserName = dispenserName; }
}