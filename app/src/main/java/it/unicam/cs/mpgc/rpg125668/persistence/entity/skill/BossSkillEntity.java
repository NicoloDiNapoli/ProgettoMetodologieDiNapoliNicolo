package it.unicam.cs.mpgc.rpg125668.persistence.entity.skill;

import it.unicam.cs.mpgc.rpg125668.model.enumeration.SkillRarity;
import it.unicam.cs.mpgc.rpg125668.model.enumeration.SkillType;
import jakarta.persistence.*;

@Entity
@Table(name = "BOSS_SKILL")
public class BossSkillEntity extends SkillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected BossSkillEntity() {}

    public BossSkillEntity(String name, SkillRarity rarity, int damage,
                           String description, SkillType skillType) {
        super(name, rarity, damage, description, skillType);
    }

    public Long getId() { return id; }
}