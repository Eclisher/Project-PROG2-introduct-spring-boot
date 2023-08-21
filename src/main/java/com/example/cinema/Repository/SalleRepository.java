package com.example.cinema.Repository;

import com.example.cinema.Model.Client;
import com.example.cinema.Model.Salle;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SalleRepository {

    List<Salle> findAll();
    Salle findById(Long id);
    Salle save(Salle salle);
    void update(Salle salle);
    boolean delete (Long id);
    List<Salle> findByCapaciteGreaterThanEqual(int capaciteMin);
}
