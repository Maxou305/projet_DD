package fr.lecampusnumerique.main;

import fr.lecampusnumerique.Controller;
import fr.lecampusnumerique.exceptions.MauvaisChoixUtilisateur;
import fr.lecampusnumerique.exceptions.PersonnageHorsPlateauException;
import fr.lecampusnumerique.main.Menu;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, PersonnageHorsPlateauException, MauvaisChoixUtilisateur {

        Controller newController = new Controller();
        newController.start();
//
//
//
//
//
//
//        try {

//        Menu menu = new Menu();

//        } catch (SQLException e) {
//            System.out.println("Déso ta méthode pue sa mère");
//        } catch (PersonnageHorsPlateauException e) {
//            throw new RuntimeException(e);
//        }


    }
}