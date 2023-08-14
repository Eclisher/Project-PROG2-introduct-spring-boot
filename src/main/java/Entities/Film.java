package Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Film {
    private Long id;
    private String titre;
    private String realisateur;
    private int duree;
    private String genre;
    private int anneeSortie;
}
