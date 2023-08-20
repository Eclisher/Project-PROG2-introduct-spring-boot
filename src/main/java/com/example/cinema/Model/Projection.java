package com.example.cinema.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Projection {
    private int id;
    private Salle salle;
    private Film film;
    private LocalDateTime dateHeure;

    public Projection(int id, Salle salle, Film film, LocalDateTime dateHeure) {
        this.id = id;
        this.salle = salle;
        this.film = film;
        this.dateHeure = dateHeure;
    }
}
