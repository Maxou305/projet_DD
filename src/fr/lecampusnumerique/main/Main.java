package fr.lecampusnumerique.main;

import fr.lecampusnumerique.Controller;
import fr.lecampusnumerique.exceptions.MauvaisChoixUtilisateur;
import fr.lecampusnumerique.exceptions.PersonnageHorsPlateauException;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, MauvaisChoixUtilisateur, PersonnageHorsPlateauException {

        Controller newController = new Controller();
        newController.start();

    }
}