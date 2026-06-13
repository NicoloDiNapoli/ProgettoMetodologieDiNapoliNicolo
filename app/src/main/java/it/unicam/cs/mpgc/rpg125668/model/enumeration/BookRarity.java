package it.unicam.cs.mpgc.rpg125668.model.enumeration;

/**
 * Enum for the rarity of the books of the game
 * It contains the preparation, concentration required and the probability of the skill
 * It also contains the price of the book
 * The values are randomly generated based on the rarity between min and max values
 * The price is calculated based on the preparation and concentration required
 */
public enum BookRarity {

    COMMON(randInt(8, 12), randInt(18, 22), 0.10),
    RARE(randInt(18, 22), randInt(38, 42), 0.40),
    EPIC(randInt(33, 37), randInt(58, 62), 0.60),
    LEGENDARY(randInt(48, 52), randInt(78, 82), 0.85);

    private final int preparation;
    private final int concentrationRequired;
    private final double probabilitySkill;
    private final int price;

    BookRarity(int preparation, int concentrationRequired, double probabilitySkill) {
        this.preparation = preparation;
        this.concentrationRequired = concentrationRequired;
        this.probabilitySkill = probabilitySkill;
        this.price = calculatePrice(this.preparation,this.concentrationRequired);
    }

    //return a random integer between min (inclusive) and max (inclusive)
    private static int randInt(int min, int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }

    //return the price of the book based on the preparation and concentration required
    private static int calculatePrice(int preparation, int concentrationRequired) {
        return (preparation + concentrationRequired) / 2;
    }

    public int getPreparation() {return preparation;}
    public int getConcentrationRequired() {return concentrationRequired;}
    public double getProbabilitySkill() {return probabilitySkill;}

    public int getPrice() {return this.price;}
}