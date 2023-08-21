package com.example.cinema.Repository;

import com.example.cinema.Model.Reservation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository {
    List<Reservation> findAll();

    List<Reservation> findByClientId(Long clientId);

    Reservation findById(Long id);

    Reservation save(Reservation reservation , Long clientId, Long projectionId);

    boolean delete(Long id);
}
