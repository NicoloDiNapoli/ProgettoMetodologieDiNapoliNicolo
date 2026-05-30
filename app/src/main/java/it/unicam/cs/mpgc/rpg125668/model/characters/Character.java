package it.unicam.cs.mpgc.rpg125668.model.characters;

import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.ICharacter;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.ISkill;
import java.io.Serializable;
import java.util.List;

public abstract class Character<T extends ISkill> implements Serializable, ICharacter<T> {
    protected final int maxLife;
    private String name;
    private int life;
<<<<<<< Updated upstream
    private List<Skill> skills;
    public Character(String name, int maxLife, int life, List<Skill> skills) {
=======
    private final List<T> skills;

    public Character(String name, int maxLife, int life, List<T> skills) {
        if(name == null || life > maxLife || skills == null) throw new IllegalArgumentException("Illegal arguments: name is null or life > maxLife or skills is null");
>>>>>>> Stashed changes
        this.name = name;
        this.maxLife = maxLife;
        this.life = life;
        this.skills = skills;
    }

<<<<<<< Updated upstream
    public void heal(int heal){
        setLife(getLife() + heal);
    }

    public void attack(Character target, int damage){
        target.setLife(target.getLife() - damage);
    }

    public void addSkill(Skill skill){
=======
    public void addSkill(T skill){
>>>>>>> Stashed changes
        skills.add(skill);
    }

    public boolean isDead(){
        return this.life <= 0;
    }
    //Getter
    public String getName() {return this.name;}
    public int getLife() {return this.life;}
    public int getMaxLife() {return this.maxLife;}

    public List<T> getSkills() {return this.skills;}

    //Setter
    public void setLife(int life) {
        if(life > maxLife)
            this.life = maxLife;
        else this.life = Math.max(life, 0);
    }
}
