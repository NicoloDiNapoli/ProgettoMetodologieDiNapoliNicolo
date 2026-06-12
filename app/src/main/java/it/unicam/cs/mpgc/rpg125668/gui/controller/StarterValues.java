package it.unicam.cs.mpgc.rpg125668.gui.controller;

/**
 * Class that contains the starter values for a skill and player starter stats
 */
public class StarterValues {
    public final String skillName;
    public final int preparation;
    public final int life;

    public StarterValues(String skillName, int preparation, int life) {
        this.skillName = skillName;
        this.preparation = preparation;
        this.life = life;
    }
}