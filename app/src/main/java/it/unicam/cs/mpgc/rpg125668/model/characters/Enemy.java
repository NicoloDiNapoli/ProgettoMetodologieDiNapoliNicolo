package it.unicam.cs.mpgc.rpg125668.model.characters;

import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.Combatant;
import it.unicam.cs.mpgc.rpg125668.model.enumeration.EnemyDifficult;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.ISkill;

import java.io.Serializable;
import java.util.List;

public class Enemy extends Character<ISkill> implements Serializable, Combatant {
    private final EnemyDifficult difficult;
    public Enemy(String name, int life, int maxLife, EnemyDifficult difficult, List<ISkill> skills) {
        super(name, life, maxLife,skills);
        this.difficult = difficult;

    }

    //Getter
<<<<<<< Updated upstream
    public EnemyDifficult getDifficult() {return difficult;}
=======
    public EnemyDifficult getDifficult() {return this.difficult;}

    @Override
    public void useSkill(Combatant target, ISkill skill) {
        if(target == null || skill == null) throw new IllegalArgumentException("Illegal arguments: target or skill is null");
        if(skill.isHealing())
            target.heal(skill.getDamage());
        else
            target.takeDamage(skill.getDamage());
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
>>>>>>> Stashed changes
}
