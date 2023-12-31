package fr.lecampusnumerique.personnages;

import fr.lecampusnumerique.equipements.defense.EquipementDefensif;
import fr.lecampusnumerique.equipements.offense.EquipementOffensif;
import fr.lecampusnumerique.equipements.offense.guerrier.Arc;
import fr.lecampusnumerique.equipements.offense.magicien.Invisibilite;
import fr.lecampusnumerique.game.ennemis.Dragon;
import fr.lecampusnumerique.game.ennemis.Ennemi;
import fr.lecampusnumerique.game.ennemis.MauvaisEsprit;

import java.util.Scanner;

/**
 * Classe Personnage en mode abstrait, ne sera pas instancié mais sera la Classe mère de Guerrier et Magicien. Contient toutes les informations liées au personnage.
 */
public abstract class Personnage {
    private int id;
    private final String name;
    private int life;
    private int startLife;
    private int hpMax;
    private int strength;
    private int attackLevel;
    private int position;
    private boolean exitFight = false;
    private boolean winFight = false;
    private boolean buff = false;
    private EquipementOffensif offensive;
    private EquipementDefensif defensive;
    private Inventaire inventory;

    /**
     * Constructeur de Personnage prenant un seul paramètre
     *
     * @param pName nom que l'utilisateur donne à son joueur
     */
    protected Personnage(String pName) {
        name = pName;
    }

    /**
     * Constructeur de Personnage prenant 6 paramètres.
     *
     * @param pName      name
     * @param pLife      life
     * @param pStrength  strength
     * @param pHpMax     hpMax
     * @param pDefensive defensive
     */
    protected Personnage(String pName, int pLife, int pStrength, int pHpMax, EquipementOffensif pOffensive, EquipementDefensif pDefensive) {
        name = pName;
        life = pLife;
        strength = pStrength;
        hpMax = pHpMax;
        defensive = pDefensive;
        offensive = pOffensive;
        inventory = new Inventaire();
    }

    // ----- METHODES ------------------------------------------------------------------------------------

    /**
     * Méthode permettant au joueur de se soigner.
     *
     * @param healing valeur du heal
     */
    public void heal(int healing) {
        life += healing;
        if (life >= hpMax) {
            life = hpMax;
        }
    }

    /**
     * Méthode permettant de gérer l'attaque sur un ennemi.
     *
     * @param pEnnemi ennemi affronté
     */
    public void attack(Ennemi pEnnemi) {
        if (pEnnemi instanceof Dragon && offensive instanceof Arc) {
            pEnnemi.setLife(pEnnemi.getLife() - (strength + offensive.getValue() + 2));
        } else if (pEnnemi instanceof MauvaisEsprit && offensive instanceof Invisibilite) {
            pEnnemi.setLife(pEnnemi.getLife() - (strength + offensive.getValue() + 3));
        } else {
            pEnnemi.setLife(pEnnemi.getLife() - (strength + offensive.getValue()));
        }
        if (isBuff()) {
            strength /= 2;
            buff = false;
        }
    }

    /**
     * Méthode permettant de gérer les dommages subis par un ennemi.
     *
     * @param pEnnemi ennemi affronté
     */
    public void damaged(Ennemi pEnnemi) {
        int damages = pEnnemi.getAttack() - defensive.getValue();
        life -= damages;
    }

    /**
     * Méthode permettant de gérer la fuite lors d'un combat (Le joueur recule).
     */
    public void escape() {
        position -= 2;
        System.out.println("OK tu fuis, pas de bashing. Mais tu recules quand même de 2 cases. T'es maintenant sur la case " + position);
        exitFight = true;
    }

    /**
     * Méthode permettant de gérer le moment où le joueur dépasse le plateau (sa position devient supérieure à 63).
     */
    public void moveBack() {
        position = 126 - position;
        System.out.println("STOOOOOOOOOOOOOOOOOP ! Tu vas trop loin !!!!! Tu recules à la case " + position);
    }

    /**
     * Méthode permettant d'afficher les stats du joueur
     */
    public void displayStats() {
        System.out.println(this);
    }

    public void buff() {
        buff = true;
        strength *= 2;
    }

    public void switchWeapons() {
        Scanner eventUser = new Scanner(System.in);
        try {
            System.out.println("Quelle arme veux-tu récupérer ?");
            inventory.displayInventory();
            int userChoice = eventUser.nextInt();
            EquipementOffensif temp = offensive;
            setOffensive(inventory.getInventory().get(userChoice));
            inventory.getInventory().set(userChoice, temp);
            System.out.println("Armes échangées.");

        } catch (NullPointerException e) {
            System.out.println("Ton inventaire est vide frérot");
        }
    }

    // ----- GETTERS & SETTERS ------------------------------------------------------------------------------------

    public int getStartLife() {
        return startLife;
    }

    public boolean isWinFight() {
        return winFight;
    }

    public void setWinFight(boolean winFight) {
        this.winFight = winFight;
    }

    public String getName() {
        return name;
    }

    public abstract String getType();

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getHpMax() {
        return hpMax;
    }

    public int getStrength() {
        return strength;
    }

    public EquipementOffensif getOffensive() {
        return offensive;
    }

    public void setOffensive(EquipementOffensif offensive) {
        this.offensive = offensive;
    }

    public EquipementDefensif getDefensive() {
        return defensive;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isExitFight() {
        return exitFight;
    }

    public void setExitFight(boolean exitFight) {
        this.exitFight = exitFight;
    }

    public int getId() {
        return id;
    }

    public void setStartLife(int startLife) {
        this.startLife = startLife;
    }

    public boolean isBuff() {
        return buff;
    }

    public Inventaire getInventory() {
        return inventory;
    }

    public void setInventory(Inventaire inventory) {
        this.inventory = inventory;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "----- FICHE DU PERSO -----" +
                "\nNom : " + name +
                "\nClasse : " + getType() +
                "\nHP : " + life +
                "\nForce : " + strength +
                "\n" + offensive +
                "\n" + defensive +
                "\n ------------------------ INVENTAIRE ------------------------" +
                "\n" + inventory +
                "\n------------------------";
    }
}