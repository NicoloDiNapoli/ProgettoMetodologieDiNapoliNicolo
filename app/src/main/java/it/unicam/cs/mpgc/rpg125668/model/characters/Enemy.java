package it.unicam.cs.mpgc.rpg125668.model.characters;

import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.Combatant;
import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IEnemy;
import it.unicam.cs.mpgc.rpg125668.model.enumeration.EnemyDifficult;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IBossSkill;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.ISkill;
import java.util.List;

public class Enemy extends Character<IBossSkill> implements IEnemy<IBossSkill> {
    private final EnemyDifficult difficult;

    public Enemy(String name, int life, int maxLife, EnemyDifficult difficult, List<IBossSkill> skills) {
        if (difficult == null) throw new IllegalArgumentException("Illegal arguments: difficult is null");
        super(name, difficult.maxLife(), life,skills);
        this.difficult = difficult;
    }

    public EnemyDifficult getDifficult() {
        return difficult;
    }


    @Override
    public void heal(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Healing amount is negative");
        this.setLife(this.getLife() + amount);
    }

    @Override
    public void takeDamage(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Damage amount is negative");
        this.setLife(this.getLife() - amount);
    }


    @Override
    protected void setMaxLife() {
        this.maxLife = difficult.maxLife();
    }
}
