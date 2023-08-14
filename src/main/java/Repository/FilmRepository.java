package Repository;

import Entities.Film;

import java.util.List;

public interface FilmRepository {
    Film finById(Long id);
    List<Film> findAll();
    void save(Film film);
    void update(Film film);
    void delete (Long id);
}
