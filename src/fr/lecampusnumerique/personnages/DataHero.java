package fr.lecampusnumerique.personnages;

/**
 * Classe DataHero servant de passerelle entre la BDD et Magicien ou Guerrier au moment du "téléchargement" d'un héros dans le jeu depuis la BDD.
 */
public class DataHero {
    private int id;
    private String name;
    private String type;
    private int life;
    private int strength;
    private String offensive;
    private String defensive;

    /**
     * Constructeur de DataHero
     * @param id id
     * @param name name
     * @param type type
     * @param life life
     * @param strength strength
     * @param offensive offensive
     * @param defensive defensive
     */

    public DataHero(int id, String name, String type, int life, int strength, String offensive, String defensive) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.offensive = offensive;
        this.defensive = defensive;
        this.life = life;
        this.strength = strength;
    }

    @Override
    public String toString() {
        return "\n" + id + " - " + name + " (type : " + type + ", life = " + life + ", strength = " + strength + ", offensive = " + offensive + ", defensive = " + defensive + ")";
    }
}
