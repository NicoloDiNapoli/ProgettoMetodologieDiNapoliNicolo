package it.unicam.cs.mpgc.rpg125668.persistence.entity.character;

import jakarta.persistence.*;

@Entity
@Table(name = "LEVEL")
public class LevelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "level", nullable = false)
    private int level;

    @Column(name = "experience", nullable = false)
    private int experience;

    @Column(name = "next_level_experience", nullable = false)
    private int nextLevelExperience;

    protected LevelEntity() {}

    public LevelEntity(int level, int experience, int nextLevelExperience) {
        this.level = level;
        this.experience = experience;
        this.nextLevelExperience = nextLevelExperience;
    }

    public Long getId() { return id; }
    public int getLevel() { return level; }
    public int getExperience() { return experience; }
    public int getNextLevelExperience() { return nextLevelExperience; }
    public void setLevel(int level) { this.level = level; }
    public void setExperience(int experience) { this.experience = experience; }
    public void setNextLevelExperience(int nextLevelExperience) { this.nextLevelExperience = nextLevelExperience; }
}