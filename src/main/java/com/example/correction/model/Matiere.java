package com.example.correction.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_matiere")
public class Matiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    public Matiere(){}

    public Matiere(String nom){
        this.nom = nom;
    }

    public Long getId() { return id; }

    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }
}