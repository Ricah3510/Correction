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

    public Note(Long id, Matiere matiere, Candidat candidat, Correcteur correcteur, double note) {
        this.id = id;
        this.matiere = matiere;
        this.candidat = candidat;
        this.correcteur = correcteur;
        this.note = note;
    }

    

    public Note(Matiere matiere, Candidat candidat, Correcteur correcteur, double note) {
        this.matiere = matiere;
        this.candidat = candidat;
        this.correcteur = correcteur;
        this.note = note;
    }



    public Note() {
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

    public Correcteur getCorrecteur() {
        return correcteur;
    }

    public void setCorrecteur(Correcteur correcteur) {
        this.correcteur = correcteur;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }
    

}