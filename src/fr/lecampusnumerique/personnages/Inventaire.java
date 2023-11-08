package fr.lecampusnumerique.personnages;

import fr.lecampusnumerique.equipements.offense.EquipementOffensif;

import java.util.ArrayList;
import java.util.Scanner;

public class Inventaire {
    private ArrayList<EquipementOffensif> inventory;

    public Inventaire() {
        inventory = new ArrayList<>(); // TODO gérer exception
    }

    public ArrayList<EquipementOffensif> getInventory() {
        return inventory;
    }

    public void addToInventory(EquipementOffensif eo) throws ArrayIndexOutOfBoundsException {
        if (inventory.size() < 2) {
            inventory.add(eo);
            System.out.println(eo.getName() + " ajouté à l'inventaire");
        } else {
            System.out.println("Désolé ton inventaire est plein, impossible de prendre cette arme");
            updateInventory(eo);
        }
    }

    public void updateInventory(EquipementOffensif eo) {
        Scanner eventUser = new Scanner(System.in);
        displayInventory();
        System.out.println("Quelle arme veux-tu remplacer ?");
        inventory.set(eventUser.nextInt(), eo);
    }

    public void displayInventory() {
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println(i + " - " + inventory.get(i));
        }
    }

    @Override
    public String toString() {
        return "" + inventory;
    }
}