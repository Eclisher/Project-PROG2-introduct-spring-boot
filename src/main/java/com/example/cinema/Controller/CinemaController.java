package com.example.cinema.Controller;

import com.example.cinema.Model.*;
import com.example.cinema.Service.CinemaService;
import jakarta.validation.Valid;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@ComponentScan
public class CinemaController {
    @GetMapping("")
    public String Bienvenu() {
        return "Votre serveur est bien été demarrer";
    }

    @GetMapping("/ping")
    public String pingPong() {
        return "pong";
    }

    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    //Methode de la gestion des clients
    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return cinemaService.getAllClients();
    }


    @GetMapping("/clients/{id}")
    public Client getClientById(@PathVariable Long id) {
        return cinemaService.getClientById(id);
    }


    @PostMapping("/clients")
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client client){
        Client createdClient = cinemaService.createClient(client);
        if (createdClient != null) {
            return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("clients/{id}")
    public ResponseEntity<Client> updateClient(
            @PathVariable Long id,
            @Valid @RequestBody Client client) {

        Client updatedClient = cinemaService.updateClient(id, client);
        if (updatedClient != null) {
            return new ResponseEntity<>(updatedClient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/clients/{id}")
    public boolean deleteClient(@PathVariable Long id) {
        return cinemaService.deleteClient(id);
    }

    //Methode de gestion des projections
    @GetMapping("/projections")
    public List<Projection> getAllProjection() {
        return cinemaService.getAllProjections();
    }

    @GetMapping("/projections/{id}")
    public ResponseEntity<Projection> getProjectionById(@PathVariable Long id) {
        Projection projection = (Projection) cinemaService.getProjectionById(id);
        if (projection != null) {
            return ResponseEntity.ok(projection);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/projections")
    public ResponseEntity<Projection> createProjection(@RequestBody Projection projection) {
        Projection createdProjection = cinemaService.createProjection(projection);
        if (createdProjection != null) {
            return new ResponseEntity<>(createdProjection, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/projections/{id}")
    public ResponseEntity<Projection> updateProjection(@PathVariable Long id, @RequestBody Projection projection) {
        Projection updatedProjection = cinemaService.updateProjection(id, projection);
        if (updatedProjection != null) {
            return ResponseEntity.ok(updatedProjection);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/projections/{id}")
    public ResponseEntity<Void> deleteProjection(@PathVariable Long id) {
        boolean deleted = cinemaService.deleteProjection(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //Methode de gestion des salles
    @GetMapping("/salles")
    public List<Salle> getAllSalles() {
        return cinemaService.getAllSalles();
    }

    @GetMapping("/salles/{id}")
    public ResponseEntity<Salle> getSalleById(@PathVariable Long id) {
        Salle salle = cinemaService.getSalleById(id);
        if (salle != null) {
            return ResponseEntity.ok(salle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/salles")
    public ResponseEntity<Salle> createSalle(@RequestBody Salle salle) {
        Salle createdSalle = cinemaService.createSalle(salle);
        if (createdSalle != null) {
            return new ResponseEntity<>(createdSalle, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/salles/{id}")
    public ResponseEntity<Salle> updateSalle(@PathVariable Long id, @RequestBody Salle salle) {
        Salle updatedSalle = cinemaService.updateSalle(id, salle);
        if (updatedSalle != null) {
            return ResponseEntity.ok(updatedSalle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/salles/{id}")
    public ResponseEntity<Void> deleteSalle(@PathVariable Long id) {
        boolean deleted = cinemaService.deleteSalle(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Methode de gestion des films
    @GetMapping("/films")
    public List<Film> getAllFilms() {
        return cinemaService.getAllFilms();
    }

    @GetMapping("/films/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable Long id) {
        Film film = cinemaService.getFilmById(id);
        if (film != null) {
            return ResponseEntity.ok(film);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/films")
    public ResponseEntity<Film> createFilm(@RequestBody Film film) {
        Film createdFilm = cinemaService.createFilm(film);
        if (createdFilm != null) {
            return new ResponseEntity<>(createdFilm, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/films/{id}")
    public ResponseEntity<Film> updateFilm(@PathVariable Long id, @RequestBody Film film) {
        Film updatedFilm = cinemaService.updateFilm(id, film);
        if (updatedFilm != null) {
            return ResponseEntity.ok(updatedFilm);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/films/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        boolean deleted = cinemaService.deleteFilm(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Methode de gestion du réservation


    @PostMapping("/reservations")
    public ResponseEntity<Reservation> createReservation(
            @RequestBody Reservation reservation,
            @RequestParam Long clientId,
            @RequestParam Long projectionId) {
        Reservation createdReservation = cinemaService.createReservation(reservation, clientId, projectionId);
        if (createdReservation != null) {
            return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reservations")
    public List<Reservation> getAllReservations() {
        return cinemaService.getAllReservations();
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        boolean deleted = cinemaService.deleteReservation(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Bonnus
    // Methode de chercher un projection  à la date ...
        // en entrant par exemple http://localhost:8080/upcoming?dateTime=2022-10-10%2011:59:50
    @GetMapping("/upcoming")
    public List<Projection> getUpcomingProjectionsByDateTime(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime dateTime) {
        return cinemaService.getProjectionsByDateTime(dateTime);
    }


    // Méthode de recherche de salles par capacité
    //http://localhost:8080/salles/search?capaciteMin=900
    // le chiffre 900 designe le nombre de capacité minimum de ce que vous chercher
    @GetMapping("/salles/search")
    public List<Salle> getSallesByCapacite(@RequestParam int capaciteMin) {
        return cinemaService.getSallesByCapacite(capaciteMin);
    }

    //Methode de trouver un reservations d'un client par un id
    @GetMapping("/clients/{id}/reservations")
    public List<Reservation> getReservationsByClientId(@PathVariable Long id) {
        return cinemaService.getReservationsByClientId(id);
    }

    //Methode de recherche de reservation par la date spécifier
    // http://localhost:8080/reservations/date?reservationDate=2023-03-07
    @GetMapping("/reservations/date")
    public List<Reservation> getReservationsByDate(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate reservationDate) {
        return cinemaService.getReservationsByDate(reservationDate);
    }


}
