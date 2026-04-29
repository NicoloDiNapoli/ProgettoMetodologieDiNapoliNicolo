package it.unicam.cs.mpgc.rpg125668.model;

public class Studente extends Personaggio{
    protected final int preparazioneMassima = 100,concentrazioneMassima = 100;
    private int livello;
    private int preparazione;
    private int concentrazione;
    //private List<Mossa> mosse;

    /*
    @ToDo private Inventario inventario;
    */
    //private Inventario inventario;

    /*
    @ToDo Aggiungere Mosse da passare e settare
    */
    public Studente(String nome, String path, int vita,  int livello, int preparazione, int concentrazione) {
        this.setNome(nome);
        this.setVita(vita);
        this.setPath(path);
        setLivello(livello);
        setPreparazione(preparazione);
        setConcentrazione(concentrazione);

    }

    //Getter
    public int getPreparazione() {return this.preparazione;}
    public int getConcentrazione() {return this.concentrazione;}
    public int getLivello() {return this.livello;}

    //Setter
    private void setConcentrazione(int concentrazione) {if(concentrazione < 0 || concentrazione > concentrazioneMassima)throw new IllegalArgumentException("Concentrazione non valido");this.concentrazione = concentrazione;}
    private void setPreparazione(int preparazione) {if(preparazione < 0 || preparazione > preparazioneMassima)throw new IllegalArgumentException("Preparazione non valido"); this.preparazione = preparazione;}
    private void setLivello(int livello) {if(livello <0)throw new IllegalArgumentException("Livello non valido"); this.livello = livello;}


}
