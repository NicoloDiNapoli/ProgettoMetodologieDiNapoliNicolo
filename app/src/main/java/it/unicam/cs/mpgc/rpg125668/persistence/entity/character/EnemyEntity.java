package it.unicam.cs.mpgc.rpg125668.persistence.entity.character;

import it.unicam.cs.mpgc.rpg125668.model.enumeration.EnemyDifficult;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.skill.BossSkillEntity;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "ENEMY")
public class EnemyEntity extends CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficult", nullable = false)
    private EnemyDifficult difficult;

    @ManyToMany
    @JoinTable(
            name = "ENEMY_SKILL",
            joinColumns = @JoinColumn(name = "enemy_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<BossSkillEntity> skills;

    protected EnemyEntity() {}

    public EnemyEntity(String name, int life, int maxLife,
                       EnemyDifficult difficult, List<BossSkillEntity> skills) {
        super(name, life, maxLife);
        this.difficult = difficult;
        this.skills = skills;
    }

    public Long getId() { return id; }
    public EnemyDifficult getDifficult() { return difficult; }
    public List<BossSkillEntity> getSkills() { return skills; }
    public void setSkills(List<BossSkillEntity> skills) { this.skills = skills; }
}