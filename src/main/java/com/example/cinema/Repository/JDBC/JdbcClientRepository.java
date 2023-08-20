package com.example.cinema.Repository.JDBC;

import com.example.cinema.Model.Client;
import com.example.cinema.Repository.ClientRepository;
import com.example.cinema.Repository.SalleRepository;
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

    private final DataSource dataSource;

    public JdbcClientRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Client mapResultSetToClient(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String nom = resultSet.getString("nom");
        String prenom = resultSet.getString("prenom");
        String email = resultSet.getString("email");
        return new Client(id, nom, prenom, email);
    }
    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM client");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Client client = mapResultSetToClient(resultSet);
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public Client findById(Long id) {
        Client client = null;
        String sql = "SELECT * FROM client WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    client = mapResultSetToClient(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public Client save(Client client) {
        String sql = "INSERT INTO client (nom, prenom,email) VALUES (?, ?,?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, client.getNom());
            preparedStatement.setString(2, client.getPrenom());
            preparedStatement.setString(3, client.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public void update(Client client) {
        String sql = "UPDATE client SET nom = ?, prenom = ?, email= ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, client.getNom());
            preparedStatement.setString(2, client.getPrenom());
            preparedStatement.setLong(3, client.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM client WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
