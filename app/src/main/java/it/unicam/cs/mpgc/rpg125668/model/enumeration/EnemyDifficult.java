package it.unicam.cs.mpgc.rpg125668.model.enumeration;

public enum EnemyDifficult {
    EASY {
        @Override
        public double damageModifier() {return 1.10;}

        @Override
        public boolean startBoss() {return false;}
    },
    MEDIUM {
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
     * Definisce il modificatore per i danni ricevuti dall'attacco dei giocatori
     * @return double
     */
    public abstract double damageModifier();

    /**
     * Stabilisce chi deve iniziare il turno di combattimento
     * false se inizia il giocatore, true se inizia il boss
     * @return boolean
     */
    public abstract boolean startBoss();

}
