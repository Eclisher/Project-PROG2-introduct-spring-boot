package Repository.JDBC;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Repository
public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/cinema";
    private static final String USER = System.getenv("USER");
    private static final String PASSWORD = System.getenv("PASSWORD") ;

    public static Connection getConnection() {
        System.out.println("Connection à la base");
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erreur lors de la connexion à la base de données", e);
        }
    }
}

