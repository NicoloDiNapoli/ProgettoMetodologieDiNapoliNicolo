package it.unicam.cs.mpgc.rpg125668.model.characters;

import it.unicam.cs.mpgc.rpg125668.model.enumeration.EnemyDifficult;

public class Enemy extends Character {
    private EnemyDifficult difficult;
    //private List<Mossa> mosse;

    public Enemy(String name, int life, int maxLife, EnemyDifficult difficult) {
        super(name, life, maxLife);
        this.difficult = difficult;
    }

    //Getter
    public EnemyDifficult getDifficult() {return difficult;}
}
