package it.unicam.cs.mpgc.rpg125668.persistence.entity.room;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "ROOM")
@Inheritance(strategy = InheritanceType.JOINED)
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "ROOM_EXIT",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "exit_id")
    )
    private List<RoomEntity> exits;

    protected RoomEntity() {}

    public RoomEntity(String name, String description, List<RoomEntity> exits) {
        this.name = name;
        this.description = description;
        this.exits = exits;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<RoomEntity> getExits() { return exits; }
    public void addExit(RoomEntity room) { this.exits.add(room); }
}