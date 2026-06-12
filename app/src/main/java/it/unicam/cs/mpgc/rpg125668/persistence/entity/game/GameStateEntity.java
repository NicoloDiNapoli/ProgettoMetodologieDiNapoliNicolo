package it.unicam.cs.mpgc.rpg125668.persistence.entity.game;

import it.unicam.cs.mpgc.rpg125668.persistence.entity.character.StudentEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.room.RoomEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

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

    private String saveGameName;

    private LocalDateTime saveGameTime;

    protected GameStateEntity() {}

    public GameStateEntity(StudentEntity student, RoomEntity currentRoom, String saveGameName) {
        this.student = student;
        this.currentRoom = currentRoom;
        this.saveGameName = saveGameName;
        this.saveGameTime = LocalDateTime.now();
    }


    public Long getId() { return id; }
    public StudentEntity getStudent() { return student; }
    public RoomEntity getCurrentRoom() { return currentRoom; }
    public String getSaveGameName() { return saveGameName; }
    public LocalDateTime getSaveGameTime() { return saveGameTime; }
    public void setStudent(StudentEntity student) { this.student = student; }
    public void setCurrentRoom(RoomEntity currentRoom) { this.currentRoom = currentRoom; }
    public void setSaveGameName(String saveGameName) { this.saveGameName = saveGameName; }
    public void setSaveGameTime(LocalDateTime saveGameTime) { this.saveGameTime = saveGameTime; }
}