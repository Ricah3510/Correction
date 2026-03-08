package com.example.correction.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_candidat")
public class Candidat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @OneToMany(mappedBy = "candidat")
    private List<Note> notes;

    @OneToMany(mappedBy = "candidat")
    private List<NoteFinal> notesFinales;

    public Candidat(){}

    public Candidat(String nom){
        this.nom = nom;
    }

    public Long getId() { return id; }

    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }

    public List<Note> getNotes() { return notes; }

    public List<NoteFinal> getNotesFinales() { return notesFinales; }
}