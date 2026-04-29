package it.unicam.cs.mpgc.rpg125668.model;

public abstract class Personaggio {
    protected final int massimaVita = 100;
    private String nome;
    private String path;
    private int vita;


    //Getter
    public String getNome() {return this.nome;}
    public int getVita() {return this.vita;}

    //Setter
    public void setNome(String nome) {if(nome == null) {throw new NullPointerException("Nome nullo");}else if(nome.isEmpty()){ throw new IllegalArgumentException("Nome non valido");}this.nome = nome;}
    public void setVita(int vita) {if(vita > massimaVita) throw new IllegalArgumentException("Vita non valido");this.vita = vita;}
    public void setPath(String path) {if(path == null)throw new NullPointerException("Path nullo"); this.path = path;}

}
