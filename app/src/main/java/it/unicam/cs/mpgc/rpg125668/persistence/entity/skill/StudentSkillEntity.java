package it.unicam.cs.mpgc.rpg125668.persistence.entity.skill;

import it.unicam.cs.mpgc.rpg125668.model.enumeration.SkillRarity;
import it.unicam.cs.mpgc.rpg125668.model.enumeration.SkillType;
import jakarta.persistence.*;

@Entity
@Table(name = "STUDENT_SKILL")
public class StudentSkillEntity extends SkillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "preparation_required", nullable = false)
    private int preparationRequired;

    protected StudentSkillEntity() {}

    public StudentSkillEntity(String name, SkillRarity rarity, int damage,
                              String description, SkillType skillType,
                              int preparationRequired) {
        super(name, rarity, damage, description, skillType);
        this.preparationRequired = preparationRequired;
    }

    public Long getId() { return id; }
    public int getPreparationRequired() { return preparationRequired; }
}