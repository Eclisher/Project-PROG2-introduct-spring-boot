package com.example.cinema.Service;

import com.example.cinema.Model.*;
import com.example.cinema.Repository.*;
import com.example.cinema.Repository.JDBC.JdbcSalleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CinemaService {
    private final ClientRepository clientRepository;
    private final SalleRepository salleRepository;
    private final ProjectionRepository projectionRepository;
    private final FilmRepository filmRepository;
    private final ReservationRepository reservationRepository;

    // Méthodes pour la gestion des clients
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client client) {
        Client existingClient = clientRepository.findById(id);
        if (existingClient != null) {
            existingClient.setNom(client.getNom());
            return clientRepository.save(existingClient);
        }
        return null;
    }

    public boolean deleteClient(Long id) {
        return clientRepository.delete(id);
    }

    //Methode de gestino de salle
    public List<Salle> getAllSalles() {
        return salleRepository.findAll();
    }

    public Salle getSalleById(Long id) {
        return salleRepository.findById(id);
    }

    public Salle createSalle(Salle salle) {
        return salleRepository.save(salle);
    }

    public Salle updateSalle(Long id, Salle salle) {
        Salle existingSalle = salleRepository.findById(id);
        if (existingSalle != null) {
            existingSalle.setNom(salle.getNom());
            return salleRepository.save(existingSalle);
        }
        return null;
    }

    public boolean deleteSalle(Long id) {
        return salleRepository.delete(id);
    }

    public List<Salle> getSallesByCapacite(int capaciteMin) {
        return salleRepository.findByCapaciteGreaterThanEqual(capaciteMin);
    }

    //Methode de gestion des projections
    public List<Projection> getAllProjections() {
        return projectionRepository.findALl();
    }

    public Projection getProjectionById(Long id) {
        return projectionRepository.findById(id);
    }

    public List<Projection> getProjectionsByDateTime(LocalDateTime dateTime) {
        return projectionRepository.findByDateTime(dateTime);
    }

    public Projection createProjection(Projection projection) {
        return projectionRepository.save(projection);
    }

    public Projection updateProjection(Long id, Projection projection) {
        Projection existingProjection = projectionRepository.findById(id);
        if (existingProjection != null) {
            existingProjection.setDateHeure(projection.getDateHeure());
            return projectionRepository.save(existingProjection);
        }
        return null;
    }

    public boolean deleteProjection(Long id) {
        return projectionRepository.delete(id);
    }

    // Méthodes pour la gestion des films
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    public Film getFilmById(Long id) {
        return filmRepository.findById(id);
    }

    public Film createFilm(Film film) {
        return filmRepository.save(film);
    }

    public Film updateFilm(Long id, Film film) {
        Film existingFilm = filmRepository.findById(id);
        if (existingFilm != null) {
            existingFilm.setTitre(film.getTitre());
            return filmRepository.save(existingFilm);
        }
        return null;
    }
    public boolean deleteFilm(Long id) {
        return filmRepository.delete(id);
    }

    //Methode de gestion de reservation

    public Reservation createReservation(Reservation reservation, Long clientId, Long projectionId) {
        return reservationRepository.save(reservation, clientId, projectionId);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
    public boolean deleteReservation(Long id) {
        return reservationRepository.delete(id);
    }

    public List<Reservation> getReservationsByClientId(Long clientId) {
        return reservationRepository.findByClientId(clientId);
    }
    public List<Reservation> getReservationsByDate(LocalDate reservationDate) {
        return reservationRepository.findByDate(reservationDate);
    }



}
