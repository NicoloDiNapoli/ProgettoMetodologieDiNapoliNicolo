package it.unicam.cs.mpgc.rpg125668.logic.manager;

import it.unicam.cs.mpgc.rpg125668.logic.enumeration.CombatResult;
import it.unicam.cs.mpgc.rpg125668.logic.manager.interfaces.ICombatManager;
import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IEnemy;
import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IStudent;
import it.unicam.cs.mpgc.rpg125668.model.enumeration.EnemyDifficult;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IBossSkill;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;

import java.util.List;
import java.util.Random;

public class CombatManager implements ICombatManager {
    private final IStudent<IStudentSkill> player;
    private final List<IEnemy<IBossSkill>> enemies;

    public CombatManager(IStudent<IStudentSkill> player, List<IEnemy<IBossSkill>> enemies) {
        if (player == null || enemies == null) throw new IllegalArgumentException("Player or enemies cannot be null");
        this.player = player;
        this.enemies = enemies;
    }

    @Override
    public CombatResult fight(IStudentSkill skill) {
        if (skill == null) throw new IllegalArgumentException("Skill cannot be null");
        if (enemies.isEmpty()) return CombatResult.WIN;

        IEnemy<IBossSkill> enemy = this.enemies.getFirst();

        // Boss strikes first if it's a boss-type enemy
        if (enemy.getDifficult().startBoss()) {
            enemyAttack(enemy);
            if (this.player.isDead()) return CombatResult.LOSE;
        }

        // Player must still have preparation to attack
        if (!playerCanAttack()) return CombatResult.LOSE;

        this.player.useSkill(enemy, skill);
        this.player.setPreparation(this.player.getPreparation() - skill.getPreparationRequired());

        if (enemy.getLife() <= 0) {
            this.player.addExperience(enemy.getDifficult().experienceReward());
            this.enemies.removeFirst();
            return CombatResult.WIN;
        }

        // Normal enemy strikes after the player
        if (!enemy.getDifficult().startBoss()) {
            enemyAttack(enemy);
            if (this.player.isDead()) return CombatResult.LOSE;
        }

        return CombatResult.FIGHT;
    }

    /**
     * Returns true if the player has enough preparation to use at least one skill.
     */
    public boolean playerCanAttack() {
        return this.player.getSkills().stream()
                .anyMatch(s -> s.getPreparationRequired() <= this.player.getPreparation());
    }

    /**
     * Enemy attacks the player (or heals itself if conditions are met).
     */
    private void enemyAttack(IEnemy<IBossSkill> enemy) {
        boolean useHealing = (enemy.getDifficult() == EnemyDifficult.MEDIUM || enemy.getDifficult() == EnemyDifficult.HARD)
                && enemy.getLife() <= 50
                && new Random().nextBoolean();

        if (useHealing) {
            enemy.getSkills().stream()
                    .filter(IBossSkill::isHealing)
                    .findFirst()
                    .ifPresent(s -> enemy.heal(s.getDamage()));
        } else {
            List<IBossSkill> attackSkills = enemy.getSkills().stream()
                    .filter(s -> !s.isHealing())
                    .toList();
            IBossSkill skill = attackSkills.get(new Random().nextInt(attackSkills.size()));
            enemy.useSkill(this.player, skill);
        }
    }

    /**
     * Executes only the enemy's turn (used when the player uses an item in combat).
     */
    @Override
    public CombatResult enemyTurn() {
        if (enemies.isEmpty()) return CombatResult.WIN;
        IEnemy<IBossSkill> enemy = this.enemies.getFirst();
        enemyAttack(enemy);
        if (this.player.isDead()) return CombatResult.LOSE;
        return CombatResult.FIGHT;
    }

    /** Returns the current enemy, or null if all enemies are defeated. */
    public IEnemy<IBossSkill> getCurrentEnemy() {
        return this.enemies.isEmpty() ? null : this.enemies.getFirst();
    }

    public boolean allEnemiesDefeated() {
        return this.enemies.isEmpty();
    }
}