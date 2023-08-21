package com.example.cinema.Repository.JDBC;

import com.example.cinema.Model.Client;
import com.example.cinema.Model.Projection;
import com.example.cinema.Model.Reservation;
import com.example.cinema.Repository.ClientRepository;
import com.example.cinema.Repository.ProjectionRepository;
import com.example.cinema.Repository.ReservationRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Repository
public class JdbcReservationRepository implements ReservationRepository {

    private final DataSource dataSource;
    private final ClientRepository clientRepository; // Inject ClientRepository
    private final ProjectionRepository projectionRepository;

    public JdbcReservationRepository(DataSource dataSource, ClientRepository clientRepository, ProjectionRepository projectionRepository) {
        this.dataSource = dataSource;
        this.clientRepository = clientRepository;
        this.projectionRepository = projectionRepository;
    }

    private Reservation mapResultSetToReservation(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Long clientId = resultSet.getLong("id_client");
        Long projectionId = resultSet.getLong("id_projection");
        LocalDateTime reservationDate = resultSet.getObject("reservation_date", LocalDateTime.class);
        Client client = clientRepository.findById(clientId);
        Projection projection = projectionRepository.findById(projectionId);
        return new Reservation(id,clientId,projectionId,reservationDate);
    }
    @Override
    public List<Reservation> findAll() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservation";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Reservation reservation = mapResultSetToReservation(resultSet);
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    @Override
    public List<Reservation> findByClientId(Long clientId) {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservation WHERE id_client = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, clientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Reservation reservation = mapResultSetToReservation(resultSet);
                    reservations.add(reservation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    @Override
    public Reservation findById(Long id) {
        String query = "SELECT * FROM reservation WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToReservation(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Reservation save(Reservation reservation, Long clientId, Long projectionId) {
        String insertQuery = "INSERT INTO reservation (client_id, projection_id) VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, clientId);
            statement.setLong(2, projectionId);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating reservation failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    reservation.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating reservation failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservation;
    }


    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM reservation WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
