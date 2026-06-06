package it.unicam.cs.mpgc.rpg125668.persistence.entity.character;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class CharacterEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "life", nullable = false)
    private int life;

    @Column(name = "max_life", nullable = false)
    private int maxLife;

    protected CharacterEntity() {}

    public CharacterEntity(String name, int life, int maxLife) {
        this.name = name;
        this.life = life;
        this.maxLife = maxLife;
    }

    public String getName() { return name; }
    public int getLife() { return life; }
    public int getMaxLife() { return maxLife; }
    public void setName(String name) { this.name = name; }
    public void setLife(int life) { this.life = life; }
    public void setMaxLife(int maxLife) { this.maxLife = maxLife; }
}