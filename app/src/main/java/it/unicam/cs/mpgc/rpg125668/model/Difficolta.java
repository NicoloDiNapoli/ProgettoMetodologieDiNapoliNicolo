package it.unicam.cs.mpgc.rpg125668.model;

public enum Difficolta {
    FACILE {
        @Override
        public double modificatoreDanno() {return 1.10;}

        @Override
        public boolean iniziaBoss() {return false;}
    },
    MEDIA {
        @Override
        public double modificatoreDanno() {return 1.0;}

        @Override
        public boolean iniziaBoss() {return false;}
    },
    DIFFICILE {
        @Override
        public double modificatoreDanno() {return 0.85;}

        @Override
        public boolean iniziaBoss() {return true;}
    };

    /**
     * Definisce il modificatore per i danni ricevuti dall'attacco dei giocatori
     * @return double
     */
    public abstract double modificatoreDanno();

    /**
     * Stabilisce chi deve iniziare il turno di combattimento
     * false se inizia il giocatore, true se inizia il boss
     * @return boolean
     */
    public abstract boolean iniziaBoss();

}
