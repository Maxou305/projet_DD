package fr.lecampusnumerique.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    public static Connection getConnection() {
        Properties properties = new Properties();
        FileInputStream fis = null;
        Connection connection = null;
        try {
            fis = new FileInputStream("db.properties");
            properties.load(fis);

            // load the Driver Class
            Class.forName(properties.getProperty("DB_DRIVER_CLASS"));

            // create the connection now
            connection = DriverManager.getConnection(properties.getProperty("DB_URL"),
                    properties.getProperty("DB_USERNAME"),
                    properties.getProperty("DB_PASSWORD"));
        } catch (IOException | ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
}