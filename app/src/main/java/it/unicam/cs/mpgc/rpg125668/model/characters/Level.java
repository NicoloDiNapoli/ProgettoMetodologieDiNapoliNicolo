package it.unicam.cs.mpgc.rpg125668.model.characters;

public class Level {
    private int level;
    private int experience;
    private int nextLevelExperience;

    public Level(int level, int experience, int nextLevelExperience) {
        if (level < 1 || experience < 0 || nextLevelExperience <= 0)
            throw new IllegalArgumentException("Illegal arguments");
        this.level = level;
        this.experience = experience;
        this.nextLevelExperience = nextLevelExperience;
    }

    public void addExperience(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        this.experience += amount;
        if (isLevelUp()) levelUp();
    }

    public boolean isLevelUp() {
        return this.experience >= this.nextLevelExperience;
    }

    private void levelUp() {
        this.experience -= this.nextLevelExperience;
        this.level++;
        this.nextLevelExperience *= 2;
    }

    public int getLevel() { return this.level; }
    public int getExperience() { return this.experience; }
    public int getNextLevelExperience() { return this.nextLevelExperience; }
}