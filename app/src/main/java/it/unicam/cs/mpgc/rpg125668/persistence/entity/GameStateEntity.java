package it.unicam.cs.mpgc.rpg125668.persistence.entity;

import it.unicam.cs.mpgc.rpg125668.persistence.entity.character.StudentEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.room.RoomEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "GAME_STATE")
public class GameStateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "current_room_id", nullable = false)
    private RoomEntity currentRoom;

    protected GameStateEntity() {}

    public GameStateEntity(StudentEntity student, RoomEntity currentRoom) {
        this.student = student;
        this.currentRoom = currentRoom;
    }

    public Long getId() { return id; }
    public StudentEntity getStudent() { return student; }
    public RoomEntity getCurrentRoom() { return currentRoom; }
    public void setStudent(StudentEntity student) { this.student = student; }
    public void setCurrentRoom(RoomEntity currentRoom) { this.currentRoom = currentRoom; }
}