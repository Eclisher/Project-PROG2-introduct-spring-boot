package com.example.cinema.Repository;

import com.example.cinema.Model.Projection;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectionRepository {
    List<Projection> findALl();
    Projection findById(Long id);
    Projection save (Projection projection);
    void update (Projection projection);
    boolean delete(Long id);

}
