package it.unicam.cs.mpgc.rpg125668.model.enumeration;

import com.google.gson.annotations.SerializedName;

public enum EnemyDifficult {
    @SerializedName("EASY")
    EASY {
        @Override
        public double damageModifier() {return 1.10;}

        @Override
        public boolean startBoss() {return false;}
    },
    @SerializedName("MEDIUM")
    MEDIUM {
        @Override
        public double damageModifier() {return 1.0;}

        @Override
        public boolean startBoss() {return false;}
    },
    @SerializedName("HARD")
    HARD {
        @Override
        public double damageModifier() {return 0.85;}

        @Override
        public boolean startBoss() {return true;}
    };

    /**
     * Defines a moltiplicator to upgrade damage at target
     * @return double
     */
    public abstract double damageModifier();

    /**
     * Define who start the fight, true start boss, false start student
     * @return boolean
     */
    public abstract boolean startBoss();

}
