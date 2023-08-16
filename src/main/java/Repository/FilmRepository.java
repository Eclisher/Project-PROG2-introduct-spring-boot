package Repository;

import Entities.Film;

import java.util.List;

public interface FilmRepository {
    Film finById(Long id);

    Film findById(Long id);

    List<Film> findAll();
    Film save(Film film);
    void update(Film film);
    boolean delete (Long id);
}
