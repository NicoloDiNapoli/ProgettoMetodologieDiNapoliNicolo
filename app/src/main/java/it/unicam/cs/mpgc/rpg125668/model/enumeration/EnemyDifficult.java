package it.unicam.cs.mpgc.rpg125668.model.enumeration;

/**
 * Enum that represents the difficult of the enemies
 * It contains damage modifier, start boss, experience reward and max life
 * The values are based on the difficulty of the enemies
 */
public enum EnemyDifficult {
    EASY(1.10, false, 20, 150),
    MEDIUM(1.0, false, 50, 200),
    HARD(0.85, true, 80, 300);

    private final double damageModifier;
    private final boolean startBoss;
    private final int experienceReward;
    private final int maxLife;

    EnemyDifficult(double damageModifier, boolean startBoss, int experienceReward, int maxLife) {
        this.damageModifier = damageModifier;
        this.startBoss = startBoss;
        this.experienceReward = experienceReward;
        this.maxLife = maxLife;
    }

    public double damageModifier() { return damageModifier; }
    public boolean startBoss() { return startBoss; }
    public int experienceReward() { return experienceReward; }
    public int maxLife() { return maxLife; }
}
