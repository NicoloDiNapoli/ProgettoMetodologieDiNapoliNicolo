package it.unicam.cs.mpgc.rpg125668.model.enumeration;

public enum BookRarity {

    COMMON(10, 20, 0.10),
    RARE(20, 40, 0.40),
    EPIC(35, 60, 0.60),
    LEGENDARY(50, 80, 0.85);

    private final int preparation;
    private final int concentrationRequired;
    private final double probabilitySkill;

    BookRarity(int preparation, int concentrationRequired, double probabilityMove) {
        this.preparation = preparation;
        this.concentrationRequired = concentrationRequired;
        this.probabilitySkill = probabilityMove;
    }

    public int getPreparation() {return preparation;}
    public int getConcentrationRequired() {return concentrationRequired;}
    public double getProbabilitySkill() {return probabilitySkill;}
}