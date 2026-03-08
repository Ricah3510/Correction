package com.example.correction.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_operateur")
public class Operateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String operateur;

    public Operateur(){}

    public Operateur(String operateur){
        this.operateur = operateur;
    }

    public Long getId() { return id; }

    public String getOperateur() { return operateur; }

    public void setOperateur(String operateur) { this.operateur = operateur; }
}