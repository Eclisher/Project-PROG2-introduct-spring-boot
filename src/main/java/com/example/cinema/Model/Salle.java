package com.example.cinema.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class Salle {
    private int id;
    private String nom;
    private int capacite;
    private Projection projections;
}
