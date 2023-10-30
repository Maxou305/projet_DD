package fr.lecampusnumerique.main;

import fr.lecampusnumerique.personnages.Personnage;

import java.sql.*;  // pour les programmes standards de JDBC
import java.util.InputMismatchException;
import java.util.Scanner;


public class ConnexionBDD {
    Connection conMyDB;
    Scanner eventUser = new Scanner(System.in);

    public boolean Connect() {
        try {
            conMyDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/donjons_dragons", "root", "root");
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur : problème de driver !");
            return false;
        }
    }

    /**
     * Permet de récupérer tous les héros de la BDD.
     *
     * @throws SQLException renvoie une erreur si la requête SQL n'est pas passée.
     */
    public void displayHeroes() throws SQLException {
        Statement stmt = conMyDB.createStatement(); // objet Statement permettant d'initialiser un statement pour la future requête SQL.
        ResultSet rs = stmt.executeQuery("SELECT * FROM Hero"); // objet ResultSet tableau dont les colonnes sont celles qui ont été extraites par notre requête SQL, et dont les lignes sont les résultats de cette requête.
        while (rs.next()) { // .next() pour les ResultSet permet de passer à la ligne suivante et donc d'itérer la table récupérée.
            System.out.println("id : " + rs.getInt("id")); //.getInt() permet de récupérer un int en lui donnant le label de la colonne.
            System.out.println("type : " + rs.getString("type"));//.getString() permet de récupérer un String en lui donnant le label de la colonne.
            System.out.println("name : " + rs.getString("name"));
            System.out.println("life : " + rs.getInt("life"));
            System.out.println("strength : " + rs.getInt("strength"));
            System.out.println("offensive : " + rs.getString("offensive"));
            System.out.println("defensive : " + rs.getString("defensive"));
        }
    }

    public void displayHeroesID() throws SQLException {
        Statement stmt = conMyDB.createStatement(); // objet Statement permettant d'initialiser un statement pour la future requête SQL.
        ResultSet rs = stmt.executeQuery("SELECT * FROM Hero");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " - " + rs.getString("name"));
        }
    }

    public ResultSet getHeroByID(int pUserChoice) throws SQLException {
        int userChoice = pUserChoice;
        String sql = "SELECT * FROM hero WHERE id = ?";
        ResultSet rs = null;
        try {
            PreparedStatement stmt = conMyDB.prepareStatement(sql);
            stmt.setInt(1, userChoice);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.getMessage();
        }
        System.out.println("C'EST RENTRE DEDANS OMG");
        return rs;
    }



    public void createHero(Personnage pPlayer) throws SQLException {
        try {
            String sql = "INSERT INTO hero(type, name, life, strength, offensive, defensive) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conMyDB.prepareStatement(sql);
            stmt.setString(1, pPlayer.getType());
            stmt.setString(2, pPlayer.getName());
            stmt.setInt(3, pPlayer.getLife());
            stmt.setInt(4, pPlayer.getStrength());
            stmt.setString(5, pPlayer.getOffensive().getName());
            stmt.setString(6, pPlayer.getDefensive().getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    /**
     * Permet de modifier n'importe quel personnage présent dans la BDD.
     * La méthode utilise la méthode displayHeroesID() puis crée un nouveau personnage temporaire en fonction du type choisi.
     * Les données du personnage temporaire sont ensuite récupérées et remplacées dans la BDD.
     *
     * @throws SQLException renvoie une exception qsi la commande SQL ne passe pas.
     */
    public void editHero(Personnage pPlayer) throws SQLException, InputMismatchException {
        try {
            PreparedStatement stmt = conMyDB.prepareStatement("UPDATE hero SET name = ?, life = ?, type = ?, strength = ?, offensive = ?, defensive = ? WHERE id = ?");
            stmt.setString(1, pPlayer.getName());
            stmt.setInt(2, pPlayer.getLife());
            stmt.setString(3, pPlayer.getType());
            stmt.setInt(4, pPlayer.getStrength());
            stmt.setString(5, pPlayer.getOffensive().getName());
            stmt.setString(6, pPlayer.getDefensive().getName());
            stmt.setInt(7, pPlayer.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problème dans l'update du nom");
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Problème : tu t'es trompé de type d'input !");
        }
        System.out.println("-------------------\nPersonnage modifié\n-------------------");
    }
    public void saveHeroInDB(Personnage pPlayer) throws SQLException {
        try {
            PreparedStatement stmt = conMyDB.prepareStatement("UPDATE hero SET name = ?, life = ?, type = ?, strength = ?, offensive = ?, defensive = ? WHERE id = ?");
            stmt.setString(1, pPlayer.getName());
            stmt.setInt(2, pPlayer.getLife());
            stmt.setString(3, pPlayer.getType());
            stmt.setInt(4, pPlayer.getStrength());
            stmt.setString(5, pPlayer.getOffensive().getName());
            stmt.setString(6, pPlayer.getDefensive().getName());
            stmt.setInt(7, pPlayer.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problème dans la save du héros : " + e.getMessage());
        }
        System.out.println("-------------------\nPersonnage sauvegardé\n-------------------");
    }

    public void changeLifePoints(Personnage pPlayer) {
        try {
            PreparedStatement stmt = conMyDB.prepareStatement("UPDATE hero SET life = ? WHERE id = ?");
            stmt.setInt(1, pPlayer.getLife());
            stmt.setInt(2, pPlayer.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problème dans l'update de la vie : " + e.getMessage());
        }
    }
}