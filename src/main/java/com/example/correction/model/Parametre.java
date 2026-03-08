package com.example.correction.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_parametre")
public class Parametre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_matiere")
    private Matiere matiere;

    private double diff;

    @ManyToOne
    @JoinColumn(name = "id_operateur")
    private Operateur operateur;

    @ManyToOne
    @JoinColumn(name = "id_resolution")
    private Resolution resolution;

}