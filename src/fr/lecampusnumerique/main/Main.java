package fr.lecampusnumerique.main;

import fr.lecampusnumerique.main.Menu;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        Menu menu = new Menu();
        try {

            menu.start();

        } catch (SQLException e) {
            System.out.println("Déso ta méthode pue sa mère");
        }


    }
}