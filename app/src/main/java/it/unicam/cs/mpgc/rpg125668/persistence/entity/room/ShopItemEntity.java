package it.unicam.cs.mpgc.rpg125668.persistence.entity.room;

import it.unicam.cs.mpgc.rpg125668.persistence.entity.item.ItemEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "SHOP_ITEM")
public class ShopItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private ItemEntity item;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "shop_room_id")
    private ShopRoomEntity shopRoom;

    protected ShopItemEntity() {}

    public ShopItemEntity(ItemEntity item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Long getId() { return id; }
    public ItemEntity getItem() { return item; }
    public int getQuantity() { return quantity; }
    public ShopRoomEntity getShopRoom() { return shopRoom; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setItem(ItemEntity item) { this.item = item; }
    public void setShopRoom(ShopRoomEntity shopRoom) { this.shopRoom = shopRoom; }

}