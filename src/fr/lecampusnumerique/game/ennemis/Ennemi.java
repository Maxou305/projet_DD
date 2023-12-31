package fr.lecampusnumerique.game.ennemis;

import fr.lecampusnumerique.game.iCell;
import fr.lecampusnumerique.personnages.Personnage;

import java.util.Scanner;

/**
 * Classe abstraite permettant la création d'ennemis. Les Classes Dragon, Gobelin, Magicien, Orcs et MauvaisEsprits en héritent.
 * Implémente l'interface iCell.
 */
public abstract class Ennemi implements iCell {
    private int life;
    private int attack;
    private String name;

    public Ennemi(String pName, int pAttack, int pLife) {
        name = pName; attack = pAttack; life = pLife;
    }

    // ----- GETTERS & SETTERS ------------------------------------------------------------------------------------

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public abstract String getType();

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public String getName() {
        return name;
    }

    /**
     * Méthode issue de l'interface iCell permettant l'interaction du joueur avec un Ennemi (fuite ou combat).
     * @param pPlayer joueur
     */
    @Override
    public void interaction(Personnage pPlayer) {
        Scanner userChoice = new Scanner(System.in);
        System.out.println("Alerte ! Vous avez rencontré un " + name + this);
        System.out.println("Que voulez-vous faire ?\n1 - Attaquer\n2 - Changer d'arme\n3 - Fuir");
        switch (userChoice.nextInt()){
            case 1 -> fight(pPlayer);
            case 2 -> pPlayer.switchWeapons();
            case 3 -> pPlayer.escape();
        }
    }

    /**
     * Méthode prenant un paramètre et permettant la gestion des combats (fuite ou attaque des deux protagonistes)
     * @param pPlayer joueur
     */
    public void fight(Personnage pPlayer) {
        Scanner userChoice = new Scanner(System.in);
        while (life > 0 && pPlayer.getLife() > 0 && !pPlayer.isWinFight() && !pPlayer.isExitFight()) {
            pPlayer.attack(this);
            if (life > 0) {
                pPlayer.damaged(this);
                if (pPlayer.getLife() < 0) {
                    System.out.println("Déso t'es mort...");
                    break;
                }
                System.out.println("Il vous reste " + pPlayer.getLife() + " HP, l'ennemi en a " + life + ". Que voulez-vous faire ?\n1 - Attaquer\n2 - Fuir");
                if (userChoice.nextInt() == 2) {
                    int result = 1 + (int) (Math.random() * ((6 - 1) + 1));
                    pPlayer.setPosition(pPlayer.getPosition() - result);
                    System.out.println("OK tu fuis, pas de bashing. Mais tu recules quand même de " + result + " cases. T'es maintenant sur la case " + pPlayer.getPosition());
                    pPlayer.setExitFight(true);
                }
            } else {
                System.out.println("Bravo tu as éliminé l'ennemi ! Il te reste " + pPlayer.getLife() + " HP");
                pPlayer.setWinFight(true);
            }
        }
    }

    @Override
    public String toString() {
        return "\n----- FICHE DE L'ENNEMI ----" +
                "\nNom : " + name +
                "\nLife : " + life +
                "\nAttaque : " + attack +
                "\n--------------------------";

    }
}