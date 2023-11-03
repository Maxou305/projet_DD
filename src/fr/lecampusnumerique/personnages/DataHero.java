package fr.lecampusnumerique.personnages;

public class DataHero {
    private int id;
    private String name;
    private String type;
    private int life;
    private int strength;
    private String offensive;
    private String defensive;

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
