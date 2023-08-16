package Repository.JDBC;

import Entities.Film;
import Entities.Projection;
import Entities.Salle;
import Repository.FilmRepository;
import Repository.ProjectionRepository;
import Repository.SalleRepository;
import org.springframework.stereotype.Repository;


import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcProjectionRepository implements ProjectionRepository {
    private SalleRepository salleRepository;
    private FilmRepository filmRepository;

    private final DataSource dataSource;

    public JdbcProjectionRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Projection> findALl() {
        List<Projection> projections = new ArrayList<>();
        String query = "SELECT * FROM projections";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Projection projection = mapResultSetToProjection(resultSet);
                projections.add(projection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projections;
    }

    @Override
    public Projection findById(Long id) {
        String query = "SELECT * FROM projections WHERE id = ?";
        Projection projection = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, Math.toIntExact(id));
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    projection = mapResultSetToProjection(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projection;
    }

    private Projection mapResultSetToProjection(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int salleId = resultSet.getInt("salle_id");
        Long filmId = resultSet.getLong("film_id");
        Film film = filmRepository.finById(filmId);
        Salle salle = salleRepository.findById((long) salleId);
        LocalDateTime dateHeure = resultSet.getObject("date_heure", LocalDateTime.class);
        return new Projection(id, salle, film, dateHeure);
    }

    @Override
    public Projection save(Projection projection) {
        String query = "INSERT INTO projections (salle_id, film_id, date_heure) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, projection.getSalle().getId());
            preparedStatement.setInt(2, Math.toIntExact(projection.getFilm().getId()));
            preparedStatement.setObject(3, projection.getDateHeure());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating projection failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    projection.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating projection failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projection;
    }

    @Override
    public void update(Projection projection) {
        String query = "UPDATE projections SET salle_id = ?, film_id = ?, date_heure = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, projection.getSalle().getId());
            preparedStatement.setInt(2, Math.toIntExact(projection.getFilm().getId()));
            preparedStatement.setObject(3, projection.getDateHeure());
            preparedStatement.setInt(4, projection.getId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating projection failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM projections WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, Math.toIntExact(id));

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting projection failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
