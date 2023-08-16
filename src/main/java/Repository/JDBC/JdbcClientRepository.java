package Repository.JDBC;

import Entities.Client;
import Repository.ClientRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcClientRepository implements ClientRepository {
    public final DataSource dataSource;

    public JdbcClientRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Client> findAll() {
        List<Client> client = new ArrayList<>();
        String query = "SELECT * FROM clients";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                client.add(mapResultSetToClient(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    private Client mapResultSetToClient(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String nom = resultSet.getString("nom");
        String prenom = resultSet.getString("prenom");
        String email = resultSet.getString("email");
        return new Client(id, nom, prenom, email);
    }

    @Override
    public Client findById(Long id) {
        Client client = null;
        String sql = "SELECT * FROM clients WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    client = mapResultSetToClient(resultSet);
                }
            }
        } catch (SQLException e) {
            // Handle exception
        }
        return client;

    }

    @Override
    public Client save(Client client) {
        String sql = "INSERT INTO clients (nom, prenom) VALUES (?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, client.getNom());
            preparedStatement.setString(2, client.getPrenom());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle exception
        }
        return client;
    }

    @Override
    public void update(Client client) {
        String sql = "UPDATE clients SET nom = ?, prenom = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, client.getNom());
            preparedStatement.setString(2, client.getPrenom());
            preparedStatement.setLong(3, client.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle exception
        }
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM clients WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle exception
        }
        return false;
    }
}
