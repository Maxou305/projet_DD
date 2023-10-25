package fr.lecampusnumerique.main;

import java.sql.*;  // pour les programmes standards de JDBC


public class ConnexionBDD {
    Connection conMyDB;

    public ConnexionBDD() {
        try {
            conMyDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/donjons_dragons", "root", "root");
        } catch (SQLException e) {
            System.out.println("Erreur : problème de driver !");
            System.exit(1);
        }
    }
    public void getHeroes() throws SQLException {
        Statement stmt = conMyDB.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Hero");
        while(rs.next()) {
            System.out.println("id : " + rs.getInt("id"));
            System.out.println("type : " + rs.getString("type"));
            System.out.println("name : " + rs.getString("name"));
        }
    }

    public void createHero(){}
    public void editHero(){}
    public void changeLifePoints(){}
}