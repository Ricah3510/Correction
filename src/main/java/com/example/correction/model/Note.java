package com.example.correction.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_matiere")
    private Matiere matiere;

    @ManyToOne
    @JoinColumn(name = "id_candidat")
    private Candidat candidat;

    @ManyToOne
    @JoinColumn(name = "id_correcteur")
    private Correcteur correcteur;

    private double note;

}