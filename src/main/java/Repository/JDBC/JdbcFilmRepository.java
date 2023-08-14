package Repository.JDBC;

import Entities.Film;
import Repository.FilmRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcFilmRepository  implements FilmRepository {

    public  final DataSource dataSource;

    public  JdbcFilmRepository (DataSource dataSource){
        this.dataSource= dataSource;
    }
    @Override
    public Film finById(Long id) {
        String query = "SELECT * FROM films WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToFilm(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Film mapResultSetToFilm(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String titre = resultSet.getString("titre");
        String realisateur = resultSet.getString("realisateur");
        int duree = resultSet.getInt("duree_minutes");
        String genre= resultSet.getString("genre");
        int anneeSortie=resultSet.getInt("année de sortie");
        return new Film(id, titre, realisateur, duree,genre,anneeSortie);
    }

    @Override
    public List<Film> findAll() {
        List<Film> films = new ArrayList<>();
        String query = "SELECT * FROM films";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                films.add(mapResultSetToFilm(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return films;
    }

    @Override
    public void save(Film film) {
        String query = "INSERT INTO films (titre, realisateur, duree_minutes) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, film.getTitre());
            statement.setString(2, film.getRealisateur());
            statement.setInt(3, film.getDuree());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Film film) {
        String query = "UPDATE films SET titre = ?, realisateur = ?, duree_minutes = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, film.getTitre());
            statement.setString(2, film.getRealisateur());
            statement.setInt(3, film.getDuree());
            statement.setLong(4, film.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM films WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
