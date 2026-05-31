package it.unicam.cs.mpgc.rpg125668.model.characters;

import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.ICharacter;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.ISkill;
import java.io.Serializable;
import java.util.List;

public abstract class Character<T extends ISkill> implements Serializable, ICharacter<T> {
    protected int maxLife;
    private final String name;
    private int life;
    private final List<T> skills;

    public Character(String name, int maxLife, int life, List<T> skills) {
        if(name == null || life > maxLife || skills == null) throw new IllegalArgumentException("Illegal arguments: name is null or life > maxLife or skills is null");
        this.name = name;
        this.maxLife = maxLife;
        this.life = life;
        this.skills = skills;
    }


    public void addSkill(T skill){
        if(skill == null) throw new IllegalArgumentException("Illegal arguments: skill is null");
        if(skills.contains(skill)) return;
        skills.add(skill);
    }

    public boolean isDead(){
        return this.life <= 0;
    }

    public List<T> getSkills() {
        return this.skills;
    }

    //Getter
    public String getName() {return this.name;}
    public int getLife() {return this.life;}
    public int getMaxLife() {return this.maxLife;}


    //Setter
    public void setLife(int life) {
        if(life > maxLife)
            this.life = maxLife;
        else this.life = Math.max(life, 0);
    }

    protected void setMaxLife() {
    }
}
