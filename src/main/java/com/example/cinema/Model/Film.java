package com.example.cinema.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Film {
    private Long id;
    private String titre;
    private String realisateur;
    private int duree;
    private String genre;
    private int anneeSortie;

}
