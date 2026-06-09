package it.unicam.cs.mpgc.rpg125668.persistence.entity.character;

import it.unicam.cs.mpgc.rpg125668.persistence.entity.inventory.InventoryEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.skill.StudentSkillEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "STUDENT")
public class StudentEntity extends CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "preparation", nullable = false)
    private int preparation;

    @Column(name = "max_preparation", nullable = false)
    private int maxPreparation;

    @Column(name = "concentration", nullable = false)
    private int concentration;

    @Column(name = "max_concentration", nullable = false)
    private int maxConcentration;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "level_id")
    private LevelEntity level;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "inventory_id")
    private InventoryEntity inventory;

    @ManyToMany
    @JoinTable(
            name = "STUDENT_ACQUIRED_SKILL",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<StudentSkillEntity> skills;

    protected StudentEntity() {}

    public StudentEntity(String name, int life, int maxLife,
                         int preparation, int maxPreparation,
                         int concentration, int maxConcentration,
                         LevelEntity level, InventoryEntity inventory,
                         List<StudentSkillEntity> skills) {
        super(name, life, maxLife);
        this.preparation = preparation;
        this.maxPreparation = maxPreparation;
        this.concentration = concentration;
        this.maxConcentration = maxConcentration;
        this.level = level;
        this.inventory = inventory;
        this.skills = skills != null ? skills : new ArrayList<>();
    }

    public Long getId() { return id; }
    public int getPreparation() { return preparation; }
    public int getMaxPreparation() { return maxPreparation; }
    public int getConcentration() { return concentration; }
    public int getMaxConcentration() { return maxConcentration; }
    public LevelEntity getLevel() { return level; }
    public InventoryEntity getInventory() { return inventory; }
    public List<StudentSkillEntity> getSkills() { return skills; }
    public void setId(Long id) { this.id = id; }
    public void setPreparation(int preparation) { this.preparation = preparation; }
    public void setMaxPreparation(int maxPreparation) { this.maxPreparation = maxPreparation; }
    public void setConcentration(int concentration) { this.concentration = concentration; }
    public void setMaxConcentration(int maxConcentration) { this.maxConcentration = maxConcentration; }
    public void setLevel(LevelEntity level) { this.level = level; }
    public void setInventory(InventoryEntity inventory) { this.inventory = inventory; }
    public void setSkills(List<StudentSkillEntity> skills) { this.skills = skills != null ? skills : new ArrayList<>(); }
}