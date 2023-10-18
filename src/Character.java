import java.util.Scanner;

public class Character {
    private String name;
    private String type;
    private int life;
    private int strength;
    private EquipementOffensif offensive;
    private EquipementDefensif defensive;

    public Character() {
        this("Toto");
    }

    public Character(String pName) {
        this(pName, "guerrier");
    }

    public Character(String pName, String pType) {
        this.name = pName;
        this.type = pType;
        setOffensive(pType);
        setDefensive(pType);
        setStats(pType);
    }

    private void setStats(String pType) {
        if (type.equalsIgnoreCase("guerrier")) {
            this.type = "guerrier";
            this.life = 10;
            this.strength = 10;
        } else {
            this.type = "magicien";
            this.life = 6;
            this.strength = 15;
        }
    }

    private void setOffensive(String pType) {
        EquipementOffensif weapon = new EquipementOffensif(pType);
        this.offensive = weapon;
    }

    private void setDefensive(String pType) {
        EquipementDefensif shield = new EquipementDefensif(pType);
        this.defensive = shield;
    }
    public void modifyStats(){
        Scanner eventUser = new Scanner(System.in);
        System.out.println("Modifier nom : ");
        String newName = eventUser.nextLine();
        System.out.println("Modifier type : ");
        String newType = eventUser.nextLine();
        this.name = newName;
        this.type = newType;
        setOffensive(newType);
        setDefensive(newType);
    }
    public void displayStats(){
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "----- FICHE DU PERSO -----" +
                "\nNom : " + name +
                "\nType : " + type +
                "\nLife : " + life +
                "\nStrength : " + strength +
                "\nOffensive : " + offensive +
                "\nDefensive : " + defensive +
                "\n--------------------------";
    }
}