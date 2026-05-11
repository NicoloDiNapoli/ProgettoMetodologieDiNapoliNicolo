package it.unicam.cs.mpgc.rpg125668.model.enumeration;

import com.google.gson.annotations.SerializedName;

public enum BookRarity {

    @SerializedName("COMMON")
    COMMON(10, 20, 0.10),
    @SerializedName("RARE")
    RARE(20, 40, 0.40),
    @SerializedName("EPIC")
    EPIC(35, 60, 0.60),
    @SerializedName("LEGENDARY")
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