package com.example.correction.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_note_final")
public class NoteFinal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_matiere")
    private Matiere matiere;

    @ManyToOne
    @JoinColumn(name = "id_candidat")
    private Candidat candidat;

    public NoteFinal() {
    }

    private double note;

    public NoteFinal(Long id, Matiere matiere, Candidat candidat, double note) {
        this.id = id;
        this.matiere = matiere;
        this.candidat = candidat;
        this.note = note;
    }

    public NoteFinal(Matiere matiere, Candidat candidat, double note) {
        this.matiere = matiere;
        this.candidat = candidat;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

}