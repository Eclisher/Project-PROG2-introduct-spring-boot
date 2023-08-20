package com.example.cinema.Controller;

import com.example.cinema.Model.Client;
import com.example.cinema.Model.Film;
import com.example.cinema.Model.Projection;
import com.example.cinema.Model.Salle;
import com.example.cinema.Service.CinemaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CinemaController {
    @GetMapping("")
    public String Bienvenu(){
        return "Votre Serveur est bien démmarrer";
    }
    @GetMapping("/ping")
    public String pingPong(){
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
    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    @PostMapping("/clients") // Assurez-vous que le chemin correspond à votre requête POST
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client createdClient = cinemaService.createClient(client);
        if (createdClient != null) {
            return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/clients/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client) {
        return cinemaService.updateClient(id, client);
    }

    @DeleteMapping("/clients/{id}")
    public boolean deleteClient(@PathVariable Long id) {
        return cinemaService.deleteClient(id);
    }

    //Methode de gestion des projections
    @GetMapping("/projections")
    public List<Projection> getAllProjection(){
        return  cinemaService.getAllProjections();
    }

    @GetMapping("/projections/{id}")
    public Projection getProjectionById(@PathVariable Long id){
        return (Projection) cinemaService.getProjectionById(id);
    }

    //Methode de gestion des salles
    @GetMapping("/salles")
    public List<Salle> getAllSalles(){
        return  cinemaService.getAllSalles();
    }

    //Methode de gestion des films
    @GetMapping("/films")
    public List<Film> getAllFilms(){
        return cinemaService.getAllFilms();
    }
    @GetMapping("/films/{id}")
    public Film getFilmById(@PathVariable Long id){
        return cinemaService.getFilmById(id);
    }
}
