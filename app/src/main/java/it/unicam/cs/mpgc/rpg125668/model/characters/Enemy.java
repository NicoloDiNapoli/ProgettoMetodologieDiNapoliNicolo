package it.unicam.cs.mpgc.rpg125668.model.characters;

import it.unicam.cs.mpgc.rpg125668.model.enumeration.EnemyDifficult;
import it.unicam.cs.mpgc.rpg125668.model.skill.Skill;

import java.io.Serializable;
import java.util.List;

public class Enemy extends Character implements Serializable {
    private final EnemyDifficult difficult;

    public Enemy(String name, int life, int maxLife, EnemyDifficult difficult, List<Skill> skills) {
        super(name, life, maxLife,skills);
        this.difficult = difficult;

    }

    //Getter
    public EnemyDifficult getDifficult() {return difficult;}
}
