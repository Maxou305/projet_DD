public class Character {
    private String name;
    private int life;
    private int strength;
    private String offensive;
    private String defensive;

    public Character() {
        this.name = "Toto";
        this.life = 10;
        this.strength = 10;
        this.offensive = "carrot";
        this.defensive = "leaf";
    }
    public Character(String pName) {
        this.name = pName;
        this.life = 5;
        this.strength = 5;
        this.offensive = "bread";
        this.defensive = "pillow";
    }
    public Character(String pName, String type) {
        this.name = pName;
        if (type.equalsIgnoreCase("guerrier")){
            this.life = 10;
            this.strength = 10;
            this.offensive = "sword";
            this.defensive = "shield";
        }
        if (type.equalsIgnoreCase("magicien")){
            this.life = 6;
            this.strength = 15;
            this.offensive = "spell";
            this.defensive = "philtre";
        }
    }
    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", life=" + life +
                ", strength=" + strength +
                ", offensive='" + offensive + '\'' +
                ", defensive='" + defensive + '\'' +
                '}';
    }
}