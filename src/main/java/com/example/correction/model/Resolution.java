package com.example.correction.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_resolution")
public class Resolution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    public Resolution(){}

    public Resolution(String nom){
        this.nom = nom;
    }

    public Long getId() { return id; }

    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }
}