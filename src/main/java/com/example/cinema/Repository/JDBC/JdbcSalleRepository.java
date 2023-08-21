package com.example.cinema.Repository.JDBC;

import com.example.cinema.Model.Film;
import com.example.cinema.Model.Projection;
import com.example.cinema.Model.Salle;
import com.example.cinema.Repository.FilmRepository;
import com.example.cinema.Repository.ProjectionRepository;
import com.example.cinema.Repository.SalleRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class JdbcSalleRepository  implements SalleRepository {
    private SalleRepository salleRepository;
    private FilmRepository filmRepository;
    private ProjectionRepository  projectionRepository;
    public  final DataSource dataSource;

    public JdbcSalleRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Salle mapResultSetToSalle(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nom = resultSet.getString("nom");
        int capacite = resultSet.getInt("capacite");
        Long id_projection = resultSet.getLong("id_projection"); // Récupérer l'ID de la projection

        return new Salle(id, nom, capacite, Math.toIntExact(id_projection));
    }

    private Projection mapResultSetToProjection(ResultSet resultSet) throws SQLException {
        int id_projection = resultSet.getInt("id");
        int salleId = resultSet.getInt("id_salle");
        Long filmId = resultSet.getLong("film_id");
        Film film = filmRepository.finById(filmId);
        Salle salle = salleRepository.findById((long) salleId);
        LocalDateTime dateHeure = resultSet.getObject("date_heure", LocalDateTime.class);
        return new Projection(id_projection, salle, film, dateHeure /* autres attributs */

    );
    }
    @Override
    public List<Salle> findAll() {
        List<Salle> salles = new ArrayList<>();
        String query = "SELECT * FROM salle";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Salle salle = mapResultSetToSalle(resultSet);
                salles.add(salle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salles;
    }

    @Override
    public Salle findById(Long id) {
        String query = "SELECT * FROM salle WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToSalle(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Salle save(Salle salle) {
        String query = "INSERT INTO salle (nom, capacite) VALUES (?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, salle.getNom());
            statement.setInt(2, salle.getCapacite());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salle;
    }

    @Override
    public void update(Salle salle) {
        String query = "UPDATE salle SET nom = ?, capacite = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, salle.getNom());
            statement.setInt(2, salle.getCapacite());
            statement.setLong(3, salle.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM salle WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
