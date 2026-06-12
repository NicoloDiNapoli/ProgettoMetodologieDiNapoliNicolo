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
        if(player == null || enemies == null) throw new IllegalArgumentException("Player or enemies cannot be null");
        this.player = player;
        this.enemies = enemies;
    }

    public CombatResult fight(IStudentSkill skill) {
        if (skill == null) throw new IllegalArgumentException("Skill cannot be null");
        IEnemy<IBossSkill> enemy = this.enemies.getFirst();

        if (enemy.getDifficult().startBoss()) {
            enemyAttack(enemy);
            if (this.player.isDead()) return CombatResult.LOSE;
        }

        //if player can't attack it returns lose.
        if (!playerCanAttack()) return CombatResult.LOSE;
        this.player.useSkill(enemy, skill);
        this.player.setPreparation(this.player.getPreparation() - skill.getPreparationRequired());

        if (enemy.getLife() <= 0) {
            this.player.addExperience(enemy.getDifficult().experienceReward());
            this.enemies.removeFirst();
            return CombatResult.WIN;
        }

        //boss fight
        if (!enemy.getDifficult().startBoss()) {
            enemyAttack(enemy);
            if (this.player.isDead()) return CombatResult.LOSE;
        }

        return CombatResult.FIGHT;
    }

    /**
     * it defines if the player has a lot of preparation to use any skill.
     * @return true if a player can attack, false otherwise
     */
    public boolean playerCanAttack() {
        return this.player.getSkills().stream()
                .anyMatch(s -> s.getPreparationRequired() <= this.player.getPreparation());
    }

    /**
     * If difficult of the nemy is medium or higer, it generates a random skill to use.
     * if generate a healing skill, it uses it on himself.
     * else it uses a random skill to attack the player.
     * @param enemy boss to fight
     */
    private void enemyAttack(IEnemy<IBossSkill> enemy) {
        //set a random boolean to decide if use a healing skill or not.
        boolean useHealing = (enemy.getDifficult() == EnemyDifficult.MEDIUM || enemy.getDifficult() == EnemyDifficult.HARD)
                && enemy.getLife() <= 50
                && new Random().nextBoolean();

        //if it uses a healing skill, use it on himself.
        //else it uses an attack skill on target
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

    public IEnemy<IBossSkill> getCurrentEnemy() {
        return this.enemies.getFirst();
    }

    public boolean allEnemiesDefeated() {
        return this.enemies.isEmpty();
    }
}
