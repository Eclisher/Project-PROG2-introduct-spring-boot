package com.example.cinema.Repository;

import com.example.cinema.Model.Film;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FilmRepository {
    Film finById(Long id);

    Film findById(Long id);

    List<Film> findAll();
    Film save(Film film);
    void update(Film film);
    boolean delete (Long id);
}
