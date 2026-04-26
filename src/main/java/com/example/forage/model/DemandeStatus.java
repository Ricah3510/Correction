package com.example.forage.model;

import jakarta.persistence.*;

import java.time.Duration;
import java.util.Date;

import com.example.forage.dto.DurationConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "t_demande_status")
public class DemandeStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_demande", nullable = false)
    private Demande demande;

    @ManyToOne
    @JoinColumn(name = "id_status", nullable = false)
    private Status status;

    @Column(name = "observation")
    private String observation;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date date;

    @Column(name = "dureeplein")
    @Convert(converter = DurationConverter.class)
    private Duration dureePlein;

    @Column(name = "dureeouvert")
    @Convert(converter = DurationConverter.class)
    private Duration dureeOuvert;
    
    public Duration getDureePlein() {
        return dureePlein;
    }

    public void setDureePlein(Duration dureePlein) {
        this.dureePlein = dureePlein;
    }

    public Duration getDureeOuvert() {
        return dureeOuvert;
    }

    public void setDureeOuvert(Duration dureeOuvert) {
        this.dureeOuvert = dureeOuvert;
    }

    public DemandeStatus() {}
    
    public String getObservation() {
        return observation;
    }
    
    public void setObservation(String observation) {
        this.observation = observation;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
}