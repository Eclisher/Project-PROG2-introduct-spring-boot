package api.rest.Cinema;

import Repository.JDBC.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
public class DatabaseTest {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            System.out.println("Connexion réussie !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
