package fr.lecampusnumerique.main;

import java.sql.*;  // pour les programmes standards de JDBC
import java.math.*; // pour le support de BigDecimal et BigInteger


public class DBConnection {
    Connection conMyDB;

    public DBConnection() {
        try {
            Driver myDriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(myDriver);
            conMyDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/donjons_dragons", "root", "root");
        } catch (SQLException e) {
            System.out.println("Error: problème de driver !");
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