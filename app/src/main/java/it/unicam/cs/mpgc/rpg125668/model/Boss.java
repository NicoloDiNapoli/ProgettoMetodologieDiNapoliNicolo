package it.unicam.cs.mpgc.rpg125668.model;

public class Boss extends Personaggio{
    private Difficolta difficolta;
    //private List<Mossa> mosse;

    public Boss(String nome, String path, int vita, Difficolta difficolta) {
        this.setNome(nome);
        this.setPath(path);
        this.setVita(vita);
        setDifficolta(difficolta);
    }

    //Getter
    public Difficolta getDifficolta() {return difficolta;}

    //Setter
    public void setDifficolta(Difficolta difficolta) {if(difficolta == null)throw new NullPointerException("Difficolta nulla"); this.difficolta = difficolta;}

}
