package fr.lecampusnumerique.main;

import fr.lecampusnumerique.Controller;
import fr.lecampusnumerique.exceptions.MauvaisChoixUtilisateur;
import fr.lecampusnumerique.exceptions.PersonnageHorsPlateauException;

import java.sql.SQLException;


public class Main {
    /**
     * Constructeur de la Classe Main instanciant le Controller.
     * @param args
     * @throws SQLException
     * @throws MauvaisChoixUtilisateur
     * @throws PersonnageHorsPlateauException
     */
    public static void main(String[] args) throws SQLException, MauvaisChoixUtilisateur, PersonnageHorsPlateauException {

        Controller newController = new Controller();
        newController.start();

    }
}