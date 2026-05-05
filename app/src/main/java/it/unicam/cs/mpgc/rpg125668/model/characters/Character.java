package it.unicam.cs.mpgc.rpg125668.model.characters;

public abstract class Character {
    protected final int maxLife;
    private String name;
    private int life;


    public Character(String name, int maxLife, int life) {
        this.name = name;
        this.maxLife = maxLife;
        this.life = life;
    }

    public void heal(int heal){
        setLife(getLife() + heal);
    }

    public void attack(int damage){
        setLife(getLife() - damage);
    }

    //Getter
    public String getName() {return this.name;}
    public int getLife() {return this.life;}
    protected int getMaxLife() {return this.maxLife;}

    //Setter
    public void setName(String name) {if(name == null) {throw new NullPointerException("Nome nullo");}else if(name.isEmpty()){ throw new IllegalArgumentException("Nome non valido");}this.name = name;}
    public void setLife(int life) {if(life > maxLife) this.life = maxLife; else {this.life = life;}}

}
