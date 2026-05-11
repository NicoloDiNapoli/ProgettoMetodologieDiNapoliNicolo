package it.unicam.cs.mpgc.rpg125668.model.characters;

import it.unicam.cs.mpgc.rpg125668.model.skill.Skill;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

public abstract class Character implements Serializable {
    protected final int maxLife;
    private String name;
    private int life;
    private List<Skill> skills;
    public Character(String name, int maxLife, int life, List<Skill> skills) {
        this.name = name;
        this.maxLife = maxLife;
        this.life = life;
        this.skills = skills;
    }

    public void heal(int heal){
        setLife(getLife() + heal);
    }

    public void attack(Character target, int damage){
        target.setLife(target.getLife() - damage);
    }

    public void addSkill(Skill skill){
        skills.add(skill);
    }

    //Getter
    public String getName() {return this.name;}
    public int getLife() {return this.life;}
    public int getMaxLife() {return this.maxLife;}
    public List<Skill> getSkills() {return this.skills;}

    //Setter
    public void setLife(int life) {if(life > maxLife) this.life = maxLife; else {this.life = life;}}
    public void setSkills(List<Skill> skills) {this.skills = skills;}
}
