package it.unicam.cs.mpgc.rpg125668.model.enumeration;

public enum EnemyDifficult {
    EASY {
        @Override
        public double damageModifier() {return 1.10;}

        @Override
        public boolean startBoss() {return false;}
    },
    MEDIUM{
        @Override
        public double damageModifier() {return 1.0;}

        @Override
        public boolean startBoss() {return false;}
    },
    HARD {
        @Override
        public double damageModifier() {return 0.85;}
        @Override
        public boolean startBoss() {return true;}
    };

    /**
     * Defines a multiplier to reduce damage from the target
     * @return double
     */
    public abstract double damageModifier();

    /**
     * Define who start the fight, true start boss, false start student
     * @return boolean
     */
    public abstract boolean startBoss();

}
