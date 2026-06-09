package it.unicam.cs.mpgc.rpg125668.model.characters.interfaces;

import it.unicam.cs.mpgc.rpg125668.model.enumeration.EnemyDifficult;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IBossSkill;

/**
 * interface for all the enemies of the game
 * @param <T> type of the skill
 */
public interface IEnemy<T extends IBossSkill> extends ICharacter<T> {
    /**
     * return difficult of the Enemy
     * @return EnemyDifficult difficult of the enemy
     */
    EnemyDifficult getDifficult();
}
