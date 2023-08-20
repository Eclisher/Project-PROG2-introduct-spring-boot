package com.example.cinema.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Client {
    private Long id;
    private String nom;
    private String prenom;
    private String email;

}
