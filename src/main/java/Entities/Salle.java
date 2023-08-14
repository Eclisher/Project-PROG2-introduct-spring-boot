package Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class Salle {
    private int id;
    private String nom;
    private int capacite;
    private List<Projection> projections;
}
