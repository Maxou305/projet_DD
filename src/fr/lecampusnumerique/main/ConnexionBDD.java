package fr.lecampusnumerique.main;

import fr.lecampusnumerique.offense.guerrier.Arme;
import fr.lecampusnumerique.personnages.Guerrier;
import fr.lecampusnumerique.personnages.Magicien;
import fr.lecampusnumerique.personnages.Personnage;

import javax.xml.namespace.QName;
import java.sql.*;  // pour les programmes standards de JDBC
import java.util.Scanner;


public class ConnexionBDD {
    Connection conMyDB;
    Scanner eventUser = new Scanner(System.in);

    public ConnexionBDD() throws SQLException {
        try {
            conMyDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/donjons_dragons", "root", "root");
        } catch (SQLException e) {
            System.out.println("Erreur : problème de driver !");
            System.exit(1);
        }
    }

    /**
     * Permet de récupérer tous les héros de la BDD.
     *
     * @throws SQLException renvoie une erreur si la requête SQL n'est pas passée.
     */
    public void getHeroes() throws SQLException {
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

    public void getHeroesID() throws SQLException {
        Statement stmt = conMyDB.createStatement(); // objet Statement permettant d'initialiser un statement pour la future requête SQL.
        ResultSet rs = stmt.executeQuery("SELECT * FROM Hero");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " - " + rs.getString("name"));
        }
    }

    public Personnage createHero() throws SQLException {
        Personnage player;
        System.out.println("Entrez le nom du personnage : ");
        String pName = eventUser.nextLine();
        System.out.println("Entrez la classe : ");
        String pType = eventUser.nextLine();
        // création perso en fonction du type
        if (pType.equalsIgnoreCase("guerrier")) {
            player = new Guerrier(pName);
            System.out.println("Personnage créé");
        } else {
            player = new Magicien(pName);
            System.out.println("Personnage créé");
        }
        String sql = "INSERT INTO hero(type, name, life, strength, offensive, defensive) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conMyDB.prepareStatement(sql);
        stmt.setString(1, player.getType());
        stmt.setString(2, pName);
        stmt.setInt(3, player.getLife());
        stmt.setInt(4, player.getStrength());
        stmt.setString(5, player.getOffensive().getName());
        stmt.setString(6, player.getDefensive().getName());
        stmt.executeUpdate();
        return player;
    }

    /**
     * Permet de modifier n'importe quel personnage présent dans la BDD. La méthode utilise la méthode getHeroesID() puis créé un nouveau personnage temporaire en fonction du type choisi.
     * Les données du personnage temporaire sont ensuite récupérées et remplacées dans la BDD.
     * @throws SQLException renvoie une exception qsi la commande SQL ne passe pas.
     */
    public void editHero() throws SQLException {
        boolean playerUpdated = false;
        Personnage player = null;

        getHeroesID();

        System.out.println("Quel héros veux-tu modifier ?");
        int userChoice = eventUser.nextInt();

        System.out.println("Entrez le nouveau nom :");
        String newName = eventUser.next();
        System.out.println("Entrez la nouvelle classe :");
        String newType = eventUser.next().toLowerCase();

        while (!playerUpdated) {
            if (newType.equalsIgnoreCase("guerrier")) {
                player = new Guerrier(newName);
                playerUpdated = true;
            }
            if (newType.equalsIgnoreCase("magicien")) {
                player = new Magicien(newName);
                playerUpdated = true;
            }
        }

        PreparedStatement stmt = conMyDB.prepareStatement("UPDATE hero SET name = ?, life = ?, type = ?, strength = ?, offensive = ?, defensive = ? WHERE id = ?");
        stmt.setString(1, newName);
        stmt.setInt(2, player.getLife());
        stmt.setString(3, newType);
        stmt.setInt(4, player.getStrength());
        stmt.setString(5, player.getOffensive().getName());
        stmt.setString(6, player.getDefensive().getName());
        stmt.setInt(7, userChoice);
        stmt.executeUpdate();
        System.out.println("-------------------\nPersonnage modifié\n-------------------");
    }

    public void changeLifePoints() {

    }
}